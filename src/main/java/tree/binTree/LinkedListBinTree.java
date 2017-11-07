package tree.binTree;

import base.Iterator;

/**
 * Description: Binary Tree implemented by LinkedList
 *
 * @Author: dong
 * @Date: 2017-09-01
 * @Time: 14:45
 */
public class LinkedListBinTree<E> implements BinTree<E> {

    protected BinTreePosition root;

    public LinkedListBinTree() {
        this(null);
    }

    public LinkedListBinTree(BinTreePosition root) {
        this.root = root;
    }

    @Override
    public BinTreePosition getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int getSize() {
        return isEmpty() ? 0 : root.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty() ? -1 : root.getHeight();
    }

    @Override
    public Iterator<E> elementsPreOrder() {
        return root.elementsPreOrder();
    }

    @Override
    public Iterator<E> elementsInOrder() {
        return root.elementsInOrder();
    }

    @Override
    public Iterator<E> elementsPostOrder() {
        return root.elementsPostOrder();
    }

    @Override
    public Iterator<E> elementsLevelOrder() {
        return root.elementsLevelOrder();
    }
}
