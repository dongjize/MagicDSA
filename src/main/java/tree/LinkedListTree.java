package tree;

/*
 * Description: Tree implemented by linked list
 *
 * @Author: dong
 * @Date: 2017-08-26
 * @Time: 15:48
 */
public class LinkedListTree<E> implements Tree<E> {

    private E element;
    private LinkedListTree parent, firstChild, nextSibling;

    public LinkedListTree() {
        this(null, null, null, null);
    }

    public LinkedListTree(E e, LinkedListTree p, LinkedListTree firstChild, LinkedListTree nextSibling) {
        this.element = e;
        this.parent = p;
        this.firstChild = firstChild;
        this.nextSibling = nextSibling;
    }

    @Override
    public E getElem() {
        return this.element;
    }

    @Override
    public void setElem(E e) {
        this.element = e;
    }

    @Override
    public LinkedListTree getParent() {
        return this.parent;
    }

    @Override
    public LinkedListTree getFirstChild() {
        return this.firstChild;
    }

    @Override
    public LinkedListTree getNextSibling() {
        return this.nextSibling;
    }

    /**
     * 返回当前节点后代元素的数目，即以当前节点为根的子树的规模
     *
     * @return
     */
    @Override
    public int getSize() {
        int size = 1; //当前节点也是自己的后代
        LinkedListTree subtree = firstChild;
        while (subtree != null) {
            size += subtree.getSize();
            subtree = subtree.getNextSibling();
        }
        return size;
    }

    /**
     * 返回当前节点的高度
     * 从长子开始，以此在所有孩子中取最大高度，即可得到当前节点的高度
     *
     * @return
     */
    @Override
    public int getHeight() {
        int height = -1;
        LinkedListTree subtree = firstChild;
        while (subtree != null) {
            height = Math.max(height, subtree.getHeight());
            subtree = subtree.getNextSibling();
        }
        return height + 1;
    }

    /**
     * 返回当前节点的深度
     * 从父亲开始，一次访问各真祖先，真祖先数目就是当前节点的深度
     *
     * @return
     */
    @Override
    public int getDepth() {
        int depth = 0;
        LinkedListTree p = this.parent;
        while (p != null) {
            depth++;
            p = p.getParent();
        }
        return depth;
    }

}
