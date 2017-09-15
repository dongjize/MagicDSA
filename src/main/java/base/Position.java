package base;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-26
 * @Time: 13:35
 */
public interface Position<E> {
    /**
     * @return the element in this position
     */
    E getElem();

    /**
     * @param e new element in this position
     * @return the former element in this position
     */
    void setElem(E e);

}

