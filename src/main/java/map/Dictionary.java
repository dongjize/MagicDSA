package map;

import base.Iterator;
import priorityQueue.Entry;

/*
 * Description: 无序词典结构接口
 *
 * @Author: dong
 * @Date: 2017-09-08
 * @Time: 00:29
 */
public interface Dictionary<K, V> {
    int getSize();

    boolean isEmpty();

    Entry<K, V> find(Object key);

    Iterator<Entry<K, V>> findAll(Object key);

    Entry<K, V> insert(K key, V value);

    Entry<K, V> remove(Object key);

    Iterator<Entry<K, V>> iterator();
}
