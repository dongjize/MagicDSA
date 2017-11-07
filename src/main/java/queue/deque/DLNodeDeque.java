package queue.deque;

import linkedList.DLNode;

/**
 * Description: Deque implemented by double-ended LinkedList
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 21:30
 */
public class DLNodeDeque<E> implements Deque<E> {

    protected DLNode<E> header;
    protected DLNode<E> trailer;
    protected int size;

    public DLNodeDeque() {
        header = new DLNode<>();
        trailer = new DLNode<>();
        header.setNext(trailer);
        trailer.setPrev(header);
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E first() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("empty deque");
        }
        return (E) header.getNext().getElem();
    }

    @Override
    public E last() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("empty deque");
        }
        return (E) trailer.getPrev().getElem();
    }

    @Override
    public void insertFirst(E e) {
        DLNode<E> second = header.getNext();
        DLNode<E> first = new DLNode<>(e, header, second);
        second.setPrev(first);
        header.setNext(first);
        size++;
    }

    @Override
    public void insertLast(E e) {
        DLNode<E> secondLast = trailer.getPrev();
        DLNode<E> last = new DLNode<>(e, secondLast, trailer);
        secondLast.setNext(last);
        trailer.setPrev(last);
        size++;
    }

    @Override
    public E removeFirst() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("empty deque");
        }
        DLNode first = header.getNext();
        DLNode newFirst = first.getNext();
        header.setNext(newFirst);
        newFirst.setPrev(header);
        size--;
        return (E) first.getElem();
    }

    @Override
    public E removeLast() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("empty deque");
        }
        DLNode last = trailer.getPrev();
        DLNode newLast = last.getPrev();
        trailer.setPrev(newLast);
        newLast.setNext(trailer);
        size--;
        return (E) last.getElem();
    }

    @Override
    public void traversal() {
        DLNode p = header.getNext();
        while (p != trailer) {
            System.out.print(p.getElem() + " ");
            p = p.getNext();
        }
        System.out.println();
    }
}
