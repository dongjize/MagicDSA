package tree.complBinTree;

import sequence.Sequence;
import tree.binTree.BinTreePosition;
import tree.binTree.LinkedListBinTree;
import vector.ExtensibleArrayVector;
import vector.Vector;

/*
 * Description: Complete Binary Tree implemented by Vector
 *
 * @Author: dong
 * @Date: 2017-09-01
 * @Time: 23:05
 */
public class VectorComplBinTree extends LinkedListBinTree implements CompleteBinTree {

    private Vector tree;

    /**
     * 构造方法：默认空树
     */
    public VectorComplBinTree() {
        tree = new ExtensibleArrayVector();
        root = null;
    }

    /**
     * 构造方法：按照给定的节点序列，批量式建立完全二叉树
     *
     * @param s
     */
    public VectorComplBinTree(Sequence s) {
        this();
        if (s != null) {
            while (!s.isEmpty()) {
                addLast(s.removeFirst());
            }
        }
    }

    @Override
    public BinTreePosition getRoot() {
        return tree.isEmpty() ? null : posOfNode(0);
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public int getSize() {
        return tree.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty() ? -1 : getRoot().getHeight();
    }

    @Override
    public BinTreePosition addLast(Object e) {
        BinTreePosition node = new ComplBinTreeNode(tree, e);
        root = (BinTreePosition) tree.get(0);
        return node;
    }

    @Override
    public Object deleteLast() {
        if (isEmpty()) {
            return null;
        }
        if (getSize() == 1) {
            root = null;
        }
        return tree.removeAt(tree.getSize() - 1);
    }

    @Override
    public BinTreePosition posOfNode(int i) {
        return (BinTreePosition) tree.get(i);
    }
}
