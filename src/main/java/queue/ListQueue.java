package queue;

import linkedList.ListNode;

/*
 * Description: Queue implemented based on LinkedList
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 20:43
 */
public class ListQueue<E> implements Queue<E> {

    protected ListNode head;
    protected ListNode tail;
    protected int size;

    public ListQueue() {
        head = tail = null;
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
    public E front() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("empty queue");
        }
        return (E) head.getElem();
    }

    @Override
    public void enqueue(E element) throws RuntimeException {
        ListNode listNode = new ListNode(element, null);
        if (size == 0) {
            head = listNode;
        } else {
            tail.setNext(listNode);
        }
        tail = listNode;
        size++;
    }

    @Override
    public E dequeue() throws RuntimeException {
        if (size == 0) {
            throw new RuntimeException("empty queue");
        }
        E element = (E) head.getElem();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return element;
    }

    @Override
    public void traversal() {
        ListNode listNode = head;
        while (listNode != null) {
            System.out.print(listNode.getElem() + " ");
            listNode = listNode.getNext();
        }
    }
}
