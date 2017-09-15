package tree.searchTree;

import priorityQueue.Entry;
import tree.binTree.BinTreeNode;
import tree.binTree.BinTreePosition;

/*
 * Description: 基于链表实现的BST节点类
 *
 * @Author: dong
 * @Date: 2017-09-13
 * @Time: 23:04
 */
public class BSTreeNode<E> extends BinTreeNode<E> implements BinTreePosition<E>, Entry {

    public BSTreeNode() {
        super();
    }

    public BSTreeNode(E e, BinTreePosition<E> p, boolean asLChild, BinTreePosition<E> lChild, BinTreePosition<E> rChild) {
        super(e, p, asLChild, lChild, rChild);
    }

    @Override
    public Object getKey() {
        return ((Entry) getElem()).getKey();
    }

    @Override
    public void setKey(Object k) {
        ((Entry) getElem()).setKey(k);
    }

    @Override
    public Object getValue() {
        return ((Entry) getElem()).getValue();
    }

    @Override
    public void setValue(Object v) {
        ((Entry) getElem()).setValue(v);
    }
}
