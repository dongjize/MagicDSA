package tree.binTree;

import base.Iterator;

/*
 * Description: Binary Tree interface
 *
 * @Author: dong
 * @Date: 2017-08-26
 * @Time: 15:31
 */
public interface BinTree<E> {
    /**
     * 返回树根
     *
     * @return
     */
    BinTreePosition getRoot();

    //判断是否树空
    boolean isEmpty();

    /**
     * 返回树的规模（即树根的后代数目）
     *
     * @return
     */
    int getSize();

    /**
     * 返回树（根）的高度
     *
     * @return
     */
    int getHeight();

    /**
     * 前序遍历
     *
     * @return
     */
    Iterator<E> elementsPreOrder();

    /**
     * 中序遍历
     *
     * @return
     */
    Iterator<E> elementsInOrder();

    /**
     * 后序遍历
     *
     * @return
     */
    Iterator<E> elementsPostOrder();

    /**
     * 层次遍历
     *
     * @return
     */
    Iterator<E> elementsLevelOrder();

}
