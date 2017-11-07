package queue;

/**
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 15:33
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    /**
     * Get the head element of the queue
     *
     * @return
     * @throws RuntimeException
     */
    E front() throws RuntimeException;

    void enqueue(E element) throws RuntimeException;

    E dequeue() throws RuntimeException;

    void traversal();
}
