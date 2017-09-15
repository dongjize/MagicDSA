package base;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-20
 * @Time: 16:45
 */
public interface Iterator<E> {
    boolean hasNext();

    E getNext();
}
