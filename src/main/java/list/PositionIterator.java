package list;


import base.Iterator;
import base.Position;

/**
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-20
 * @Time: 16:36
 */
public class PositionIterator<E> implements Iterator<E> {
    private List list;
    private Position nextPosition;

    public PositionIterator() {
        list = null;
    }

    public PositionIterator(List L) {
        list = L;
        if (list.isEmpty()) {
            nextPosition = null;
        } else {
            nextPosition = list.first();//从第一个位置开始
        }
    }

    @Override
    public boolean hasNext() {
        return nextPosition != null;
    }

    @Override
    public E getNext() {
        if (!hasNext()) throw new RuntimeException("no next position");
        Position currentPosition = nextPosition;
        if (currentPosition == list.last()) {
            nextPosition = null;
        } else {
            nextPosition = list.getNext(currentPosition);
        }
        return (E) currentPosition;
    }

}
