package priorityQueue;

import sequence.DLNodeSequence;
import sequence.Sequence;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-07
 * @Time: 13:18
 */
public class HeapSortTest {
    public static void main(String[] arg) {
        PQueueSorter sorter = new PQueueSorter();
        Sequence sequence = new DLNodeSequence();
        sequence.insertLast(65);
        sequence.insertLast(84);
        sequence.insertLast(99);
        sequence.insertLast(23);
        sequence.insertLast(16);
        sequence.insertLast(31);
        sequence.insertLast(54);
        sequence.insertLast(73);
        sequence.insertLast(6);
        sequence.insertLast(88);
        sorter.sort(sequence);

    }
}
