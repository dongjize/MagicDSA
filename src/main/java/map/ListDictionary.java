package map;

import base.Iterator;
import list.List;
import priorityQueue.Entry;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-08
 * @Time: 00:29
 */
public class ListDictionary<K, V> implements Dictionary<K, V> {

    private List<Entry<K,V>> list;
    private EqualityTester tester;

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Entry<K, V> find(Object key) {
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> findAll(Object key) {
        return null;
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        return null;
    }

    @Override
    public Entry<K, V> remove(Object key) {
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return null;
    }
}
