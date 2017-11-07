package tree.complBinTree;

import tree.binTree.BinTree;
import tree.binTree.BinTreePosition;

/**
 * Description: 完全二叉树接口
 *
 * 在完全二叉树中，
1. 若节点 v 有左孩子，则 i(lchild(v)) = 2 ×i(v) + 1；
2. 若节点 v 有右孩子，则 i(rchild(v)) = 2 ×i(v) + 2；
3. 若节点 v 有父节点，则 i(parent(v)) = [(i(v) - 1)/2] = [(i(v)/2] - 1
 *
 * @Author: dong
 * @Date: 2017-09-01
 * @Time: 15:05
 */
public interface CompleteBinTree<E> extends BinTree<E> {

    /**
     * 生成并返回一个存放e的外部节点，该节点成为新的末节点
     *
     * @param e
     * @return
     */
    BinTreePosition<E> addLast(E e);

    /**
     * 删除末节点
     *
     * @return
     */
    E deleteLast();

    /**
     * 返回按照层次遍历编号为i的节点的位置，0 <= i < size()
     *
     * @param i
     * @return
     */
    BinTreePosition<E> posOfNode(int i);

}
