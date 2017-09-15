package priorityQueue;

import base.Position;
import list.DLNodeList;
import list.List;
import sequence.Sequence;

/*
 * Description: 基于有序列表的优先队列
 *
 * @Author: dong
 * @Date: 2017-09-06
 * @Time: 23:25
 */
public class SortedListPQueue<K, V> implements PQueue<K, V> {

    private List<Entry<K, V>> list;
    private Comparator comparator;

    public SortedListPQueue() {
        this(new DefaultComparator(), null);
    }

    public SortedListPQueue(Comparator c) {
        this(c, null);
    }

    public SortedListPQueue(Sequence s) {
        this(new DefaultComparator(), s);
    }

    public SortedListPQueue(Comparator c, Sequence s) {
        list = new DLNodeList<>();
        comparator = c;
        if (s != null) {
            while (!s.isEmpty()) {
                Entry<K, V> e = (Entry<K, V>) s.removeFirst();
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

    /**
     * 若queue非空，则返回其中的最小条目（并不删除）;否则，报错
     *
     * @return
     * @throws RuntimeException
     */
    @Override
    public Entry<K, V> getMin() throws RuntimeException {
        if (list.isEmpty()) {
            throw new RuntimeException("意外：优先队列为空");
        }
        return (Entry<K, V>) list.last();
    }

    /**
     * /将对象value与关键码key合成一个条目，将其插入queue中，并返回该条目
     *
     * @param key
     * @param value
     * @return
     * @throws RuntimeException
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws RuntimeException {
        Entry<K, V> entry = new DefaultEntry<>(key, value);
        //若优先队列为空，或新条目是当前最大者，则直接插入至表头
        if (list.isEmpty() || comparator.compare(((Entry) (list.first().getElem())).getKey(), entry.getKey()) > 0) {
            list.insertFirst(entry);
        } else {
            //否则从尾条目开始，不断前移，直到第一个不小于entry的条目，紧接该条目之后插入entry
            Position curPos = list.last();
            while (comparator.compare(((Entry<K, V>) (curPos.getElem())).getKey(), entry.getKey()) < 0) {
                curPos = list.getPrev(curPos);
            }
            list.insertAfter(curPos, entry);
        }
        return entry;
    }

    @Override
    public Entry<K, V> deleteMin() throws RuntimeException {
        if (list.isEmpty()) {
            throw new RuntimeException("意外：优先队列空");
        }
        return list.remove(list.last());
    }
}
