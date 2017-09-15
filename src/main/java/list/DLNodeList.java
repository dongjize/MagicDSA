package list;


import base.Iterator;
import base.Position;
import linkedList.DLNode;

/*
 * Description: List implemented by deque
 *
 * @Author: dong
 * @Date: 2017-08-20
 * @Time: 00:00
 */
public class DLNodeList<E> implements List<E> {
    protected int numElem;
    protected DLNode<E> header, trailer;

    public DLNodeList() {
        numElem = 0;//空表
        header = new DLNode<>(null, null, null);//首节点
        trailer = new DLNode<>(null, header, null);//末节点
        header.setNext(trailer);//首、末节点相互链接
    }

    /**************************** auxiliary methods ****************************/

    /**
     * 检查给定位置在列表中是否合法，若是，则将其转换为*DLNode
     *
     * @param p
     * @return
     * @throws RuntimeException
     */
    protected DLNode<E> checkPosition(Position p) throws RuntimeException {
        if (null == p) {
            throw new RuntimeException("position is null");
        }
        if (header == p) {
            throw new RuntimeException("意外：头节点哨兵位置非法");
        }
        if (trailer == p) {
            throw new RuntimeException("意外：尾结点哨兵位置非法");
        }
        return (DLNode<E>) p;
    }

    @Override
    public int getSize() {
        return numElem;
    }

    @Override
    public boolean isEmpty() {
        return numElem == 0;
    }

    @Override
    public Position first() {
        if (isEmpty()) {
            throw new RuntimeException("empty list");
        }
        return header.getNext();
    }

    @Override
    public Position last() {
        if (isEmpty()) {
            throw new RuntimeException("empty list");
        }
        return trailer.getPrev();
    }

    @Override
    public Position getNext(Position p) throws RuntimeException {
        DLNode node = checkPosition(p);
        DLNode next = node.getNext();
        if (next == trailer) {
            throw new RuntimeException("Exception: Intend to pass over the trailer");
        }
        return next;
    }

    @Override
    public Position getPrev(Position p) throws RuntimeException {
        DLNode<E> node = checkPosition(p);
        DLNode<E> prev = node.getPrev();
        if (prev == header) {
            throw new RuntimeException("Exception: Intend to pass over the header");
        }
        return prev;
    }

    @Override
    public Position insertFirst(E e) {
        DLNode<E> first = new DLNode<>(e, header, header.getNext());
        header.getNext().setPrev(first);
        header.setNext(first);
        numElem++;
        return first;
    }

    @Override
    public Position insertLast(E e) {
        DLNode<E> last = new DLNode<>(e, trailer.getPrev(), trailer);
        trailer.getPrev().setNext(last);
        trailer.setPrev(last);
        numElem++;
        return last;
    }

    @Override
    public Position insertAfter(Position p, E e) throws RuntimeException {
        DLNode<E> v = checkPosition(p);
        DLNode<E> node = new DLNode<>(e, v, v.getNext());
        v.getNext().setPrev(node);
        v.setNext(node);
        numElem++;
        return node;
    }

    @Override
    public Position insertBefore(Position p, E e) throws RuntimeException {
        DLNode<E> v = checkPosition(p);
        DLNode<E> node = new DLNode<>(e, v, v.getPrev());
        v.getPrev().setNext(node);
        v.setPrev(node);
        numElem++;
        return node;
    }

    @Override
    public E remove(Position p) throws RuntimeException {
        DLNode<E> v = checkPosition(p);
        DLNode<E> prev = v.getPrev();
        DLNode<E> next = v.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        numElem--;
        v.setPrev(null);
        v.setNext(null);
        return (E) v.getElem();
    }

    @Override
    public E removeFirst() {
        return remove(header.getNext());
    }

    @Override
    public E removeLast() {
        return remove(trailer.getPrev());
    }

    @Override
    public Object replace(Position p, Object e) throws RuntimeException {
        DLNode v = checkPosition(p);
        E oldElem = (E) v.getElem();
        v.setElem(e);
        return oldElem;
    }

    @Override
    public Iterator<E> positions() {
        return new PositionIterator<>(this);
    }

    @Override
    public Iterator<E> elements() {
        return new ElementIterator<>(this);
    }
}
