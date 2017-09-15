package priorityQueue;

import base.Sorter;
import sequence.DLNodeSequence;
import sequence.Sequence;

/*
 * Description: 基于优先队列的排序器
 *
 * @Author: dong
 * @Date: 2017-09-02
 * @Time: 15:58
 */
public class PQueueSorter<E> implements Sorter<E> {

    private Comparator<E> comparator;

    public PQueueSorter() {
        this(new DefaultComparator<E>());
    }

    public PQueueSorter(Comparator<E> c) {
        this.comparator = c;
    }

    /**
     * 直接堆排序，时间复杂度O(nlogn)
     * <p>
     * 分为两个阶段：
     * 前一阶段，将待排序的n个元素组织为一个优先队列queue
     * 后一阶段，不断将优先队列中最小的元素摘除，依次构成一个有序队列
     *
     * @param s
     */
    @Override
    public void sort(Sequence<E> s) {
        Sequence sequence = new DLNodeSequence();
        while (!s.isEmpty()) {
            E e = s.removeFirst();
            sequence.insertLast(new DefaultEntry(e, e)); //用节点元素本身作为关键码
        }
        PQueue pq = new HeapPQueue(comparator, sequence);

        //从优先队列中不断去除最小元素，插至序列末尾
        while (!pq.isEmpty()) {
            sequence.insertLast(pq.deleteMin().getValue());
            System.out.println("\t:\t" + sequence.last().getElem());
        }
    }

}
