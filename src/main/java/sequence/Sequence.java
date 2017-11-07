package sequence;

import base.Position;
import list.List;
import vector.Vector;

/**
 * Description: 序列接口，同时支持向量Vector和列表List的所有方法
 *
 * Sequence即依次排列的多个对象
 *
 * @Author: dong
 * @Date: 2017-09-01
 * @Time: 23:13
 */
public interface Sequence<E> extends Vector<E>, List<E> {
    /**
     * 若0 <= r < getSize()，则返回秩为r的元素所在的位置；否则，报错
     *
     * @param r
     * @return
     * @throws RuntimeException
     */
    Position<E> rank2Pos(int r) throws RuntimeException;

    /**
     * 若p是序列中的合法位置，则返回存放于p处的元素的秩；否则，报错
     *
     * @param p
     * @return
     * @throws RuntimeException
     */
    int pos2Rank(Position<E> p) throws RuntimeException;
}
