package stack;

import linkedList.ListNode;

/*
 * Description: Stack implemented based on list.LinkedList
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 19:04
 */
public class ListStack implements Stack {

    protected ListNode top;
    protected int size;

    public ListStack() {
        top = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public Object top() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("empty stack");
        }
        return top.getElem();
    }

    @Override
    public void push(Object element) {
        top = new ListNode(element, top);
        size++;
    }

    @Override
    public Object pop() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("empty stack");
        }
        Object temp = top.getElem();
        top = top.getNext(); //refresh the top node reference
        size--;
        return temp;
    }
}
