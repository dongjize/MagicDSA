package stack;

/*
 * Description: Stack implemented by array.
 *
 * Drawback -- Capacity is initialized statically.
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 14:08
 */
public class ArrayStack implements Stack {

    // default capacity of the array
    public static final int CAPACITY = 1024;
    // actual capacity of the array
    protected int capacity;
    protected Object[] arr;
    // position of the top element
    protected int top = -1;

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(int cap) {
        this.capacity = cap;
        arr = new Object[capacity];
    }

    @Override
    public int getSize() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    @Override
    public Object top() throws RuntimeException {
        if (getSize() == capacity) {
            throw new RuntimeException("stack overflow");
        }
        return arr[top];
    }

    @Override
    public void push(Object element) {
        if (getSize() == capacity) {
            throw new RuntimeException("stack overflow");
        }
        arr[++top] = element;
    }

    @Override
    public Object pop() throws RuntimeException {
        Object element;
        if (isEmpty()) {
            throw new RuntimeException("empty stack");
        }
        element = arr[top];
        arr[top--] = null; // set null to inform the GC of recycling the memory.
        return element;
    }
}
