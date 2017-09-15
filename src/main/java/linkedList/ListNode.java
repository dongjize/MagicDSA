package linkedList;

import base.Position;

/*
 * Description: Singly Linked List Node
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 17:19
 */
public class ListNode implements Position {
    private Object element;//数据对象
    private ListNode next;//指向后继节点

    public ListNode() {
        this(null, null);
    }

    /**
     * 指定数据对象及后继节点
     *
     * @param e data object
     * @param n next node
     */
    public ListNode(Object e, ListNode n) {
        element = e;
        next = n;
    }

    @Override
    public Object getElem() {
        return element;
    }

    @Override
    public void setElem(Object e) {
        element = e;
    }


    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode newNext) {
        next = newNext;
    }
}
