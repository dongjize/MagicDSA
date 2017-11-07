package map;

import base.Iterator;
import base.Position;
import list.DLNodeList;
import list.ElementIterator;
import list.List;
import priorityQueue.DefaultEntry;
import priorityQueue.Entry;

/**
 * Description: Map implemented based on DLNodeList
 *
 * 实现虽然简单，但效率不高。为了执行其中的 get(key)、put(key, value)或 remove(key)方法，
 * 都需要扫描整个列表，这些方法的时间复杂度都是 O(n)。
 *
 * @Author: dong
 * @Date: 2017-09-07
 * @Time: 23:00
 */
public class ListMap<K, V> implements Map<K, V> {
    private List<Entry<K, V>> list;
    private EqualityTester tester;

    public ListMap() {
        this(new DefaultEqualityTester());
    }

    public ListMap(EqualityTester t) {
        list = new DLNodeList<>();
        tester = t;
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public V get(Object key) {
        Iterator<Entry<K, V>> positions = list.positions();
        while (positions.hasNext()) {
            Position pos = (Position) positions.getNext();
            Entry<K, V> entry = (Entry<K, V>) pos.getElem();
            if (tester.equals(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 若M中不存在以key为关键码的条目，则将条目(key, value)加入到M中并返回null
     * 否则，将已有条目的数据对象替换为value，并返回原先的数据对象
     *
     * @param key
     * @param value
     */
    @Override
    public V put(K key, V value) {
        Iterator<Entry<K, V>> p = list.positions();
        while (p.hasNext()) {
            Position pos = (Position) p.getNext();
            Entry<K, V> entry = (Entry<K, V>) pos.getElem();
            if (tester.equals(entry.getKey(), key)) {
                V oldValue = entry.getValue();
                list.replace(pos, new DefaultEntry<>(key, value));
                return null;
            }
        }
        //若此循环结束，说明key尚未在M中出现，因此将新条目插至表首
        list.insertFirst(new DefaultEntry<>(key, value));
        return null;
    }

    /**
     * 若M中存在以key为关键码的条目，则删除之并返回其数据对象；否则，返回null
     *
     * @param key
     * @return
     */
    @Override
    public V remove(Object key) {
        Iterator<Entry<K, V>> p = list.positions();
        while (p.hasNext()) {
            Position pos = (Position) p.getNext();
            Entry<K, V> entry = (Entry<K, V>) pos.getElem();
            if (tester.equals(entry.getKey(), key)) {
                V oldValue = entry.getValue();
                list.remove(pos);
                return oldValue;
            }
        }
        return null;
    }

    /**
     * 返回Map所有条目的迭代器
     *
     * @return
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new ElementIterator<>(list);
    }
}
