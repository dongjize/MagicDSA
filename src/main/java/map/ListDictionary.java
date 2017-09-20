package map;

import base.Iterator;
import base.Position;
import javafx.geometry.Pos;
import list.DLNodeList;
import list.ElementIterator;
import list.List;
import priorityQueue.DefaultEntry;
import priorityQueue.Entry;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-08
 * @Time: 00:29
 */
public class ListDictionary<K, V> implements Dictionary<K, V> {

    private List<Entry<K, V>> list;
    private EqualityTester tester;

    public ListDictionary() {
        this(new DefaultEqualityTester());
    }

    public ListDictionary(EqualityTester t) {
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

    /**
     * 若词典中存在以key为关键码的条目，则返回其中的一个条目；否则，返回null
     *
     * @param key
     * @return
     */
    @Override
    public Entry<K, V> find(Object key) {
        Iterator it = list.positions();
        while (it.hasNext()) {
            Position pos = (Position) it.getNext();
            Entry entry = (Entry) pos.getElem();
            if (tester.equals(entry.getKey(), key)) {
                return entry;
            }
        }
        return null;
    }

    /**
     * 返回由关键码为key的条目组成的迭代器
     *
     * @param key
     * @return
     */
    @Override
    public Iterator<Entry<K, V>> findAll(Object key) {
        List list = new DLNodeList();
        Iterator iterator = list.positions();
        while (iterator.hasNext()) {
            Position pos = (Position) iterator.getNext();
            Entry<K, V> entry = (Entry<K, V>) pos.getElem();
            if (tester.equals(entry.getKey(), key)) {
                list.insertLast(entry);
            }
        }
        return new ElementIterator<>();
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> entry = new DefaultEntry<>(key, value);
        list.insertFirst(entry);
        return entry;
    }

    /**
     * 若词典中存在以key为关键码的条目，则将摘除其中的一个并返回；否则返回null
     *
     * @param key
     * @return
     */
    @Override
    public Entry<K, V> remove(Object key) {
        Iterator iterator = list.positions();
        while (iterator.hasNext()) {
            Position pos = (Position) iterator.getNext();
            Entry entry = (Entry<K, V>) pos.getElem();
            if (tester.equals(entry.getKey(), key)) {
                Entry<K, V> oldEntry = entry;
                list.remove(pos);
                return oldEntry;
            }
        }
        return null;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new ElementIterator<>(list);
    }
}
