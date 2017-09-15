package list;

import base.Iterator;
import base.Position;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-20
 * @Time: 16:53
 */
public class ElementIterator<E> implements Iterator<E> {
    private List list;
    private Position nextPosition;

    public ElementIterator() {
        list = null;
    }

    //构造方法
    public ElementIterator(List L) {
        list = L;
        if (list.isEmpty()) {
            nextPosition = null;
        } else {
            nextPosition = list.first();
        }
    }

    //检查迭代器中是否还有剩余的元素
    @Override
    public boolean hasNext() {
        return nextPosition != null;
    }

    @Override
    public E getNext() {
        if (!hasNext()) {
            throw new RuntimeException("Exception: no next element");
        }
        Position currentPosition = nextPosition;
        if (currentPosition == list.last()) {
            nextPosition = null;//不再有下一元素
        } else {
            nextPosition = list.getNext(currentPosition);//转向下一元素
        }
        return (E) currentPosition.getElem();
    }
}
