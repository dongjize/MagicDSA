package stack;

/**
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 13:59
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    E top() throws RuntimeException;

    void push(E element);

    E pop() throws RuntimeException;
}
