package tree.searchTree;

import base.Iterator;
import list.DLNodeList;
import list.List;
import map.Dictionary;
import priorityQueue.Comparator;
import priorityQueue.DefaultComparator;
import priorityQueue.DefaultEntry;
import priorityQueue.Entry;
import tree.binTree.BinTreePosition;
import tree.binTree.LinkedListBinTree;

/**
 * Description:
 * 基于链表式BST实现的词典结构
 * 基于BinTree进行扩充
 *
 *
 * n 个互异节点组成的二分查找树，总共有 (2n)!/[n!(n+1)!]棵。
 *
 *
 * @Author: dong
 * @Date: 2017-09-13
 * @Time: 23:14
 */
public class BSTree<E> extends LinkedListBinTree<E> implements Dictionary {

    protected Comparator comparator;
    protected BinTreePosition<E> lastV; //最后操作的节点，以便AVL树、伸展树重平衡

    public BSTree() {
        this(null, new DefaultComparator());
    }

    public BSTree(BinTreePosition<E> r) {
        this(r, new DefaultComparator());
    }

    public BSTree(BinTreePosition<E> r, Comparator c) {
        root = r;
        comparator = c;
    }

    /**
     * 若词典中存在以key为关键码的条目，则返回其中的一个条目；否则，返回null
     *
     * @param key
     * @return 如果二分查找出正确结果，则返回u；否则返回null
     */
    @Override
    public Entry find(Object key) {
        if (isEmpty()) {
            return null;
        }
        BSTreeNode u = binSearch((BSTreeNode) root, key, comparator);
        return comparator.compare(key, u.getKey()) == 0 ? (Entry) u.getElem() : null;
    }

    /**
     * 返回由关键码为key的条目组成的迭代器
     *
     * @param key
     * @return
     */
    @Override
    public Iterator<Entry> findAll(Object key) {
        List s = new DLNodeList();
        findAllNodes((BSTreeNode) root, key, s, comparator);
        return s.elements();
    }

    /**
     * Insert and return Entry(key, value)
     * <p>
     * 在二分查找树中插入一个节点需要 O(h)时间，其中 h 为被插入节点的深度。
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Entry insert(Object key, Object value) {
        Entry e = new DefaultEntry(key, value); // initiate a new element

        if (isEmpty()) { // insert as a root node
            lastV = root = new BSTreeNode(e, null, true, null, null);

        } else { // insert as a common node
            BSTreeNode p = (BSTreeNode) root; // start from root node
            boolean asLeftChild; // whether insert as p's left child
            while (true) {
                p = binSearch(p, key, comparator);
                if (comparator.compare(key, p.getKey()) < 0) { //查找失败于无左孩子节点
                    asLeftChild = true;
                    break;
                } else if (comparator.compare(key, p.getKey()) > 0) { //查找失败无右孩子节点
                    asLeftChild = false;
                    break;
                } else if (!p.hasLChild()) { //如果两key相等，那么如果p没有左孩子，则作为左孩子插入
                    asLeftChild = true;
                    break;
                } else if (!p.hasRChild()) { //如果两key相等，那么如果p没有右孩子，则作为右孩子插入
                    asLeftChild = false;
                    break;
                } else { // key == p.getKey(), p.hasLChild() == true, p.hasRChild = true
                    p = (BSTreeNode) p.getLChild(); //在左子树中继续查找（当然，在右子树中查找亦可）
                }
            }
            // insert the new node
            lastV = new BSTreeNode(e, p, asLeftChild, null, null);
        }
        return e;
    }

    /**
     * 若词典中存在以key为关键码的条目，则摘除这样的一个节点，并返回其中存放的条目；否则，返回null
     * lastV指示被删除节点的父亲
     * <p>
     * 在二分查找树中删除一个节点需要 O(h)时间，其中 h 为被删除节点的深度。
     *
     * @param key
     * @return
     */
    @Override
    public Entry remove(Object key) {
        if (isEmpty()) {
            return null;
        }
        BinTreePosition v = binSearch((BSTreeNode) root, key, comparator);
        //If search fails, return null
        if (comparator.compare(key, ((BSTreeNode) v).getKey()) != 0) {
            return null;
        }

        // If search succeeds，and v is the node to delete:
        if (v.hasLChild()) { //若v的左子树非空，在v的左子树中找出其直接前驱w
            BinTreePosition w = v.getPrev();
            //交换v和w的数据对象
            v.setElem(w.getElem());
            w.setElem(v.getElem());
            v = w; //这样相当于删除w
        }
        //至此，v至多只有一个孩子
        //下面，删除v，代之以其孩子
        lastV = v.getParent();
        BinTreePosition u = v.hasLChild() ? v.getLChild() : v.getRChild();
        //若v恰为树根，则将u作为树根
        if (lastV == null) {
            if (u != null) {
                u.secede();
                root = u;
            }

        } else {
            if (v.isLChild()) { //若v是p的左孩子，则摘出v，将u作为p的左孩子
                v.secede();
                lastV.attachL(u);
            } else { //若v是p的右孩子，则摘出v，将u作为p的右孩子
                v.secede();
                lastV.attachR(u);
            }
        }
        return (Entry) v.getElem();
    }

    /**
     * @return the iterator of all entries in the dictionary
     */
    @Override
    public Iterator<Entry> iterator() {
        List list = new DLNodeList();
        concatenate(list, (BSTreeNode) root);
        return list.elements();
    }

    /**
     * 在以v为根的子树中查找关键码为key的节点（假设该子树不为空）；
     * 为了确定是否成功，上层方法需要再检查一次返回节点的关键码
     * <p>
     * 由 n 个互异条目随机生成的 BST，平均查找长度为 O(logn)。
     * n 个互异节点组成的二分查找树，总共有 (2n)!/[n!(n+1)!]棵。
     * 由 n 个条目随机组成的 BST，平均查找长度为 O(√n))
     *
     * @param v
     * @param key
     * @param c
     * @return 若找到，则返回该节点；否则，返回被访问的最后一个节点
     */
    protected BSTreeNode binSearch(BSTreeNode v, Object key, Comparator c) {
        BSTreeNode u = v;
        while (true) { //不断地将当前节点与目标关键码做比较
            int comp = c.compare(key, u.getKey());
            if (comp < 0) {
                if (u.hasLChild()) { //若u有左孩子，递归查找左子树，否则终止于无左孩子节点
                    u = (BSTreeNode) u.getLChild();
                } else {
                    return u;
                }
            } else if (comp > 0) { //若u有右孩子，递归查找右子树，或终止于无右孩子节点
                if (u.hasRChild()) {
                    u = (BSTreeNode) u.getRChild();
                } else {
                    return u;
                }
            } else {
                // bingo
                return u;
            }
        }
    }

    /**
     * 在以v为根节点的（子）树中，递归地找出关键码为key的所有节点
     * 这些节点被组织为一个列表（借此可以生成一个迭代器）
     *
     * @param v
     * @param k
     * @param s
     * @param c
     */
    protected void findAllNodes(BSTreeNode v, Object k, List s, Comparator c) {
        if (v == null) {
            return;
        }
        int comp = c.compare(k, v.getKey());
        if (comp <= 0) {
            findAllNodes((BSTreeNode) v.getLChild(), k, s, c);
        }
        // bingo
        if (comp == 0) {
            s.insertLast(v);
        }
        if (comp >= 0) {
            findAllNodes((BSTreeNode) v.getRChild(), k, s, c);
        }
    }

    /**
     * Recursively, concatenate Entries in each node to a list (by in-order traversal)
     *
     * @param list
     * @param v
     */
    protected void concatenate(List list, BSTreeNode v) {
        if (v == null) {
            return;
        }
        concatenate(list, (BSTreeNode) v.getLChild());
        list.insertLast(v.getElem());
        concatenate(list, (BSTreeNode) v.getRChild());
    }
}
