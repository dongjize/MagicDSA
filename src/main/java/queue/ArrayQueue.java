package queue;

/**
 * Description:
 *
 * f：始终等于 Q 的首元素在数组中的下标，即指向下次出队元素的位置
 * r：始终等于 Q 的末元素的下标加一，即指向下次入队元素的位置
 * 一开始，f = r = 0，此时队空。每次有对象入队时，将其存放于 Q[r]，然后 r 加一，以指向下一
 * 单元。对称地，每次有对象出队之后，也将 f 加一，指向新的队首元素。
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 15:41
 */
public class ArrayQueue implements Queue {

    public static final int CAPACITY = 1000;
    protected int capacity;
    protected Object[] arr;
    protected int f = 0;
    protected int r = 0;

    public ArrayQueue() {
        this(CAPACITY);
    }

    public ArrayQueue(int cap) {
        this.capacity = cap;
        this.arr = new Object[capacity];
    }

    @Override
    public int getSize() {
        return (capacity - f + r) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return f == r;
    }

    @Override
    public Object front() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("empty queue");
        }
        return arr[f];
    }

    @Override
    public void enqueue(Object element) throws RuntimeException {
        if (getSize() == capacity) {
            throw new RuntimeException("queue overflow");
        }
        arr[r] = element;
        r = (r + 1) % capacity;
    }

    @Override
    public Object dequeue() throws RuntimeException {
        Object element;
        if (isEmpty()) {
            throw new RuntimeException("empty queue");
        }
        element = arr[f];
        arr[f] = null;
        f = (f + 1) % capacity;
        return element;
    }

    @Override
    public void traversal() {
        if (r >= f) {
            for (int i = f; i < r; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            for (int i = f; i < r + capacity; i++) {
                System.out.print(arr[i] + " ");
            }
        }

        System.out.println();
    }
}
