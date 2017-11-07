package map;

import base.Iterator;
import priorityQueue.Entry;

/**
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-07
 * @Time: 22:11
 */
public interface Map<K, V> {
    int getSize();

    boolean isEmpty();

    V get(Object key);

    V put(K key, V value);

    V remove(Object key);

    Iterator<Entry<K, V>> iterator();

}
