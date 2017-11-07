package linkedList;

import base.Position;

/**
 * Description: Double-ended Linked List Node
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 21:10
 */
public class DLNode<E> implements Position<E> {

    // data object
    private E element;
    // previous node
    private DLNode<E> previous;
    // next node
    private DLNode<E> next;

    public DLNode() {
        this(null, null, null);
    }

    public DLNode(E e, DLNode<E> p, DLNode<E> n) {
        element = e;
        previous = p;
        next = n;
    }

    @Override
    public E getElem() {
        return element;
    }

    @Override
    public void setElem(E e) {
        element = e;
    }

    /**
     * get next element
     *
     * @return
     */
    public DLNode<E> getNext() {
        return next;
    }

    /**
     * get previous element
     *
     * @return
     */
    public DLNode<E> getPrev() {
        return previous;
    }

    /**
     * set next element
     *
     * @param newNext
     */
    public void setNext(DLNode<E> newNext) {
        next = newNext;
    }

    /**
     * set previous element
     *
     * @param newPrev
     */
    public void setPrev(DLNode<E> newPrev) {
        previous = newPrev;
    }

}
