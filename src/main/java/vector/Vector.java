package vector;

/**
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-26
 * @Time: 14:25
 */
public interface Vector<E> {
    int getSize();

    boolean isEmpty();

    E get(int index) throws RuntimeException;

    E replaceAt(int index, E e) throws RuntimeException;

    void insertAt(int index, E e) throws RuntimeException;

    E removeAt(int index) throws RuntimeException;
}
