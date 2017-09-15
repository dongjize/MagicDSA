package tree.binTree;

import base.Iterator;
import list.DLNodeList;
import list.List;
import queue.ListQueue;

/*
 * Description: Binary tree node implemented by linked list
 *
 * @Author: dong
 * @Date: 2017-08-26
 * @Time: 20:54
 */
public class BinTreeNode<E> implements BinTreePosition<E> {

    protected E element;
    protected BinTreePosition<E> parent;
    protected BinTreePosition<E> lChild;
    protected BinTreePosition<E> rChild;
    protected int size;
    protected int height;
    protected int depth;

    public BinTreeNode() {
        this(null, null, true, null, null);
    }

    public BinTreeNode(E e, BinTreePosition<E> p, boolean asLChild, BinTreePosition<E> lChild, BinTreePosition<E> rChild) {
        this.size = 1;
        this.height = this.depth = 0;
        this.parent = this.lChild = this.rChild = null;
        this.element = e;

        // build relation to parent
        if (p != null) {
            if (asLChild) {
                p.attachL(this);
            } else {
                p.attachR(this);
            }
        }

        // build relation to children
        if (lChild != null) {
            attachL(lChild);
        }
        if (rChild != null) {
            attachR(rChild);
        }
    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    @Override
    public BinTreePosition<E> getParent() {
        return parent;
    }

    @Override
    public void setParent(BinTreePosition<E> p) {
        parent = p;
    }

    @Override
    public boolean isLeaf() {
        return !hasLChild() && !hasRChild();
    }

    @Override
    public boolean isLChild() {
        return hasParent() && this == this.getParent().getLChild();
    }

    @Override
    public boolean hasLChild() {
        return getLChild() != null;
    }

    @Override
    public BinTreePosition<E> getLChild() {
        return lChild;
    }

    @Override
    public void setLChild(BinTreePosition<E> c) {
        lChild = c;
    }

    @Override
    public boolean isRChild() {
        return hasParent() && this == this.getParent().getRChild();
    }

    @Override
    public boolean hasRChild() {
        return getRChild() != null;
    }

    @Override
    public BinTreePosition<E> getRChild() {
        return rChild;
    }

    @Override
    public void setRChild(BinTreePosition<E> c) {
        rChild = c;
    }

    @Override
    public int getSize() {
        return size;
    }

    /**
     * after children change, update size
     */
    @Override
    public void updateSize() {
        size = 1;
        if (hasLChild()) {
            size += getLChild().getSize();
        }
        if (hasRChild()) {
            size += getRChild().getSize();
        }
        if (hasParent()) { // If has parent, update parent's size recursively.
            getParent().updateSize();
        }
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void updateHeight() {
        height = 0;
        if (hasLChild()) {
            height = Math.max(height, 1 + getLChild().getHeight());
        }
        if (hasRChild()) {
            height = Math.max(height, 1 + getRChild().getHeight());
        }
        if (hasParent()) {
            getParent().updateHeight();
        }
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void updateDepth() {
        depth = hasParent() ? 1 + getParent().getDepth() : 0;
        if (hasLChild()) {
            getLChild().updateDepth();
        }
        if (hasRChild()) {
            getRChild().updateDepth();
        }
    }

    /**
     * 按照中序遍历的次序，找到当前节点的直接前驱
     *
     * @return
     */
    @Override
    public BinTreePosition<E> getPrev() {
        //若左子树非空，则其中的最大者即为当前节点的直接前驱
        if (hasLChild()) {
            return findMaxDescendant(getLChild());
        }
        //若当前节点是右孩子，则父亲即为其直接前驱
        if (isRChild()) {
            return getParent();
        }

        BinTreePosition<E> v = this;
        // 从当前节点出发，沿左child链一直上升
        while (v.isLChild()) {
            v = v.getParent(); // 此时v没有parent，或者是右child
        }
        return v.getParent(); // 返回v的parent（如果null说明没有前驱）
    }

    /**
     * 按照中序遍历的次序，找到当前节点的直接后继
     *
     * @return
     */
    @Override
    public BinTreePosition<E> getSucc() {
        // 若右子树非空，则其中的最小者即为当前节点的直接后继
        if (hasRChild()) {
            return findMinDescendant(getRChild());
        }
        // 若当前节点是左child，则parent即为其直接后继
        if (isLChild()) {
            return getParent();
        }
        BinTreePosition<E> v = this;
        //从当前节点出发沿右child链一直上升
        while (v.isRChild()) {
            v = v.getParent();
        }
        return v.getParent(); // v或者没有parent，或者是左child
    }

    /**
     * 断绝与parent的关系，返回当前节点
     *
     * @return
     */
    @Override
    public BinTreePosition<E> secede() {
        if (parent != null) {
            if (isLChild()) {
                parent.setLChild(null);
            } else {
                parent.setRChild(null);
            }
            parent.updateSize();
            parent.updateHeight();
            parent = null;
            updateDepth();
        }
        return this;
    }

    /**
     * 将节点c作为当前节点的left child
     *
     * @param c
     * @return
     */
    @Override
    public BinTreePosition<E> attachL(BinTreePosition<E> c) {
        if (hasLChild()) {
            getLChild().secede();
        }
        if (c != null) {
            c.secede();
            lChild = c;
            updateSize();
            updateHeight();
            c.updateDepth();
        }
        return this;
    }

    /**
     * 将节点c作为当前节点的right child
     *
     * @param c
     * @return
     */
    @Override
    public BinTreePosition<E> attachR(BinTreePosition<E> c) {
        if (hasRChild()) {
            getRChild().secede();
        }
        if (c != null) {
            c.secede();
            rChild = c;
            updateSize();
            updateHeight();
            c.updateDepth();
        }
        return this;
    }

    @Override
    public Iterator<E> elementsPreOrder() {
        List<E> list = new DLNodeList<>();
        preOrder(list, this);
        return list.elements();
    }

    @Override
    public Iterator<E> elementsInOrder() {
        List<E> list = new DLNodeList<>();
        inOrder(list, this);
        return list.elements();
    }

    @Override
    public Iterator<E> elementsPostOrder() {
        List<E> list = new DLNodeList<>();
        postOrder(list, this);
        return list.elements();
    }

    @Override
    public Iterator<E> elementsLevelOrder() {
        List<E> list = new DLNodeList<>();
        levelOrder(list, this);
        return list.elements();
    }

    @Override
    public E getElem() {
        return element;
    }

    @Override
    public void setElem(E e) {
        element = e;
    }


    /**
     * 在v的后代中找出最小的
     *
     * @param v
     * @return
     */
    protected static BinTreePosition findMinDescendant(BinTreePosition v) {
        if (v != null) {
            while (v.hasLChild()) {
                v = v.getLChild();
            }
        }
        return v;
    }


    /**
     * 在v的后代中找出最大的
     *
     * @param v
     * @return
     */
    protected static BinTreePosition findMaxDescendant(BinTreePosition v) {
        if (v != null) {
            while (v.hasRChild()) {
                v = v.getRChild();
            }
        }
        return v;
    }


    protected static void preOrder(List list, BinTreePosition v) {
        if (v == null) {
            return;
        }
        list.insertLast(v);
        preOrder(list, v.getLChild());
        preOrder(list, v.getRChild());
    }

    protected static void inOrder(List list, BinTreePosition v) {
        if (v == null) {
            return;
        }
        inOrder(list, v.getLChild());
        list.insertLast(v);
        inOrder(list, v.getRChild());
    }

    protected static void postOrder(List list, BinTreePosition v) {
        if (v == null) {
            return;
        }
        postOrder(list, v.getLChild());
        postOrder(list, v.getRChild());
        list.insertLast(v);
    }

    protected static void levelOrder(List list, BinTreePosition v) {
        if (v == null) {
            return;
        }
        ListQueue queue = new ListQueue<>();
        queue.enqueue(v);
        while (!queue.isEmpty()) {
            BinTreePosition p = (BinTreePosition) queue.dequeue();
            list.insertLast(p);
            if (p.hasLChild()) {
                queue.enqueue(p.getLChild());
            }
            if (p.hasRChild()) {
                queue.enqueue(p.getRChild());
            }
        }
    }


}
