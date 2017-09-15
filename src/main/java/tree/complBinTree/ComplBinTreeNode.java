package tree.complBinTree;

import tree.binTree.BinTreeNode;
import tree.binTree.BinTreePosition;
import vector.Vector;

/*
 * Description: 基于秩(Rank)实现的完全二叉树节点
 *
 * @Author: dong
 * @Date: 2017-09-01
 * @Time: 21:33
 */
public class ComplBinTreeNode<E> extends BinTreeNode<E> implements BinTreePosition<E> {
    private Vector tree;
    private int rank;

    public ComplBinTreeNode(Vector t, E e) {
        tree = t;
        element = e;
        rank = tree.getSize();
        tree.insertAt(rank, this);
    }


    @Override
    public boolean hasParent() {
        return rank == 0 ? false : true;
    }


    @Override
    public BinTreePosition<E> getParent() {
        return hasParent() ? (BinTreePosition<E>) tree.get((rank - 1) / 2) : null;
    }

    @Override
    public boolean hasLChild() {
        return rank * 2 + 1 < tree.getSize();
    }

    @Override
    public BinTreePosition<E> getLChild() {
        return hasLChild() ? (BinTreePosition<E>) tree.get(rank * 2 + 1) : null;
    }

    @Override
    public boolean hasRChild() {
        return rank * 2 + 2 < tree.getSize();
    }

    @Override
    public BinTreePosition<E> getRChild() {
        return hasRChild() ? (BinTreePosition<E>) tree.get(rank * 2 + 2) : null;
    }

    @Override
    public int getSize() {
        int size = 1;
        if (hasLChild()) {
            size += getLChild().getSize();
        }
        if (hasRChild()) {
            size += getRChild().getSize();
        }
        return size;
    }

    @Override
    public int getHeight() {
        int leftH = hasLChild() ? getLChild().getHeight() : -1;
        int rightH = hasRChild() ? getRChild().getHeight() : -1;
        return Math.max(leftH, rightH) + 1;
    }

    @Override
    public int getDepth() {
        return hasParent() ? getParent().getDepth() + 1 : 0;
    }
}
