package sequence;

import base.Position;
import linkedList.DLNode;
import list.DLNodeList;

/*
 * Description: 基于双向链表实现序列
 *
 * @Author: dong
 * @Date: 2017-09-01
 * @Time: 23:49
 */
public class DLNodeSequence<E> extends DLNodeList<E> implements Sequence<E> {

    protected void checkRank(int r, int n) throws RuntimeException {
        if (r < 0 || r >= n) {
            throw new RuntimeException("意外：非法的秩" + r + "，应该属于[0, " + n + ")");
        }
    }

    @Override
    public Position<E> rank2Pos(int r) throws RuntimeException {
        DLNode<E> node;
        // If rank is relatively small, start from header
        if (r <= getSize() / 2) {
            node = header.getNext();
            for (int i = 0; i < r; i++) {
                node = node.getNext();
            }
        } else { // If rank is relatively big, start from trailer
            node = trailer.getPrev();
            for (int i = 0; i > getSize() - r; i++) {
                node = node.getPrev();
            }
        }
        return node;
    }

    /**
     * 若p是序列中的合法位置，则返回存放于p处的元素的秩；否则，报错--O(n)
     *
     * @param p
     * @return
     * @throws RuntimeException
     */
    @Override
    public int pos2Rank(Position p) throws RuntimeException {
        DLNode node = header.getNext();
        int rank = 0;
        while (node != trailer) {
            if (node == p) {
                return rank;
            }
            node = node.getNext();
            rank++;
        }
        throw new RuntimeException("意外：作为参数的位置不属于序列");
    }

    @Override
    public E get(int index) throws RuntimeException {
        checkRank(index, getSize());
        return (E) rank2Pos(index).getElem();
    }

    @Override
    public E replaceAt(int index, E o) throws RuntimeException {
        checkRank(index, getSize());
        return (E) replace(rank2Pos(index), o);
    }

    @Override
    public void insertAt(int index, E o) throws RuntimeException {
        checkRank(index, getSize() + 1);
        if (getSize() == index) {
            insertLast(o);
        } else {
            insertBefore(rank2Pos(index), o);
        }
    }

    @Override
    public E removeAt(int index) throws RuntimeException {
        checkRank(index, getSize());
        return remove(rank2Pos(index));
    }
}
