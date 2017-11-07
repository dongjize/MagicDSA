package list;

import base.Iterator;
import base.Position;

/**
 * Description: List Interface
 *
 * @Author: dong
 * @Date: 2017-08-20
 * @Time: 00:01
 */
public interface List<E> {
    int getSize();

    boolean isEmpty();

    Position first();

    Position last();

    Position getNext(Position p) throws RuntimeException;

    Position getPrev(Position p) throws RuntimeException;

    /**
     * insert as the first element
     *
     * @param e
     * @return
     */
    Position insertFirst(E e);

    /**
     * insert as the first element
     *
     * @param e
     * @return
     */
    Position insertLast(E e);

    /**
     * Insert e after Position p
     *
     * @param p
     * @param e
     * @return
     * @throws RuntimeException
     */
    Position insertAfter(Position p, E e) throws RuntimeException;

    /**
     * Insert e before Position p
     *
     * @param p
     * @param e
     * @return
     * @throws RuntimeException
     */
    Position insertBefore(Position p, E e) throws RuntimeException;

    E remove(Position p) throws RuntimeException;

    E removeFirst();

    E removeLast();

    E replace(Position<E> p, E e) throws RuntimeException;

    /**
     * position iterator
     *
     * @return
     */
    Iterator<E> positions();

    /**
     * element iterator
     *
     * @return
     */
    Iterator<E> elements();
}
