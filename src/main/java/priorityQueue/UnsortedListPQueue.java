package priorityQueue;

import base.Iterator;
import base.Position;
import list.DLNodeList;
import list.List;
import sequence.Sequence;

/**
 * Description: 基于无序列表实现的优先队列
 *
 * @Author: dong
 * @Date: 2017-09-02
 * @Time: 16:12
 */
public class UnsortedListPQueue<K, V> implements PQueue<K, V> {

    private List<Entry<K, V>> list;
    private Comparator comparator;

    public UnsortedListPQueue() {
        this(new DefaultComparator(), null);
    }

    public UnsortedListPQueue(Comparator c) {
        this(c, null);
    }

    public UnsortedListPQueue(Sequence<Entry<K, V>> s) {
        this(new DefaultComparator(), s);
    }

    public UnsortedListPQueue(Comparator c, Sequence<Entry<K, V>> s) {
        list = new DLNodeList<>();
        comparator = c;
        if (s != null) {
            while (!s.isEmpty()) {
                Entry<K, V> e = s.removeFirst();
                insert(e.getKey(), e.getValue());
            }
        }
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
    public Entry<K, V> getMin() throws RuntimeException {
        if (list.isEmpty()) {
            throw new RuntimeException("意外：优先队列空");
        }
        Position minPos = list.first();
        Position curPos = list.getNext(minPos);
        while (curPos != null) {
            if (comparator.compare(minPos.getElem(), curPos.getElem()) > 0) {
                minPos = curPos;
            }
        }
        return (Entry<K, V>) minPos.getElem();
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws RuntimeException {
        Entry<K, V> entry = new DefaultEntry<>(key, value);
        list.insertLast(entry);
        return entry;
    }

    @Override
    public Entry<K, V> deleteMin() throws RuntimeException {
        if (list.isEmpty()) {
            throw new RuntimeException("意外：优先队列空");
        }
        Position minPos = list.first();
        Iterator iterator = list.positions();
        while (iterator.hasNext()) {//依次检查所有位置，找出最小条目
            Position curPos = (Position) (iterator.getNext());
// System.out.println("\t" + ((Entry)(curPos.getElem())).getKey());
            if (0 < comparator.compare(
                    ((Entry<K, V>) (minPos.getElem())).getKey(),
                    ((Entry<K, V>) (curPos.getElem())).getKey()))
                minPos = curPos;
        }
        return list.remove(minPos);
    }
}
