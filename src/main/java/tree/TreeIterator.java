package tree;

import base.Iterator;
import base.Position;
import list.List;
import queue.ListQueue;

/**
 * Description:
 *
 *  树的前序、后序及层次遍历，均可在 O(n)时间内完成，其中 n 为树本身的规模。
 *
 * @Author: dong
 * @Date: 2017-08-26
 * @Time: 19:45
 */
public class TreeIterator<E> implements Iterator<E> {

    private List list;
    private Position nextPosition;

    public TreeIterator() {
        list = null;
    }

    /**
     * 前序遍历
     *
     * @param tree
     */
    public void preOrderTraverse(LinkedListTree tree) {
        if (tree == null) {
            return;
        }
        list.insertLast(tree);
        LinkedListTree subTree = tree.getFirstChild();
        while (subTree != null) {
            preOrderTraverse(subTree);
            subTree = subTree.getNextSibling();
        }
    }

    /**
     * 后序遍历
     *
     * @param tree
     */
    public void postOrderTraverse(LinkedListTree tree) {
        if (tree == null) {
            return;
        }
        LinkedListTree subTree = tree.getFirstChild();
        while (subTree != null) {
            postOrderTraverse(subTree);
            subTree = subTree.getNextSibling();
        }
        list.insertLast(tree);
    }

    /**
     * 层次遍历
     * 深度小的节点优先访问
     *
     * @param tree
     */
    public void levelTraverse(LinkedListTree tree) {
        if (tree == null) {
            return;
        }
        ListQueue queue = new ListQueue();
        queue.enqueue(tree);
        while (!queue.isEmpty()) {
            LinkedListTree t = (LinkedListTree) queue.dequeue();
            list.insertLast(t);
            LinkedListTree subTree = t.getFirstChild();
            while (subTree != null) {
                queue.enqueue(subTree);
                subTree = subTree.getNextSibling();
            }
        }
    }

    @Override
    public boolean hasNext() {
        return nextPosition == null;
    }

    /**
     * 返回迭代器的下一元素
     *
     * @return
     */
    @Override
    public E getNext() {
        if (!hasNext()) {
            throw new RuntimeException("No next position");
        }
        Position currentPosition = nextPosition;
        if (currentPosition == list.last()) { //若到达尾元素，则不再有下一元素
            nextPosition = null;
        } else {
            nextPosition = list.getNext(currentPosition);//转向下一元素
        }
        return (E) currentPosition.getElem();
    }
}
