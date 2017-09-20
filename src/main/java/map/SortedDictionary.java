package map;


import base.Iterator;
import priorityQueue.Entry;

/*
 * Description: 有序词典接口
 *
 * @Author: dong
 * @Date: 2017-09-20
 * @Time: 16:48
 */
public interface SortedDictionary<K, V> extends Dictionary<K, V> {
    /**
     * 若词典非空，则返回其中关键码最小的条目；否则，返回null
     *
     * @return
     */
    Entry<K, V> first();

    /**
     * 若词典非空，则返回其中关键码最大的条目；否则，返回null
     *
     * @return
     */
    Entry<K, V> last();

    /**
     * 返回由关键码不小于key的条目依非降序组成的迭代器
     *
     * @param key
     * @return
     */
    Iterator successors(K key);

    /**
     * 返回由关键码不大于key的条目依非升序组成的迭代器
     *
     * @param key
     * @return
     */
    Iterator predecessors(K key);
}
