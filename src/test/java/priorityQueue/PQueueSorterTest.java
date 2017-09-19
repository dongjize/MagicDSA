package priorityQueue;

import org.junit.Assert;
import org.junit.Test;
import sequence.DLNodeSequence;
import sequence.Sequence;

import static org.junit.Assert.*;

public class PQueueSorterTest {
    @Test
    public void testHeapSort() {

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