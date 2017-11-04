package algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

public class SortTest {

    @Test
    public void testComparisonCountingSort() {
        int[] arr = {62, 31, 84, 96, 19, 47};
        Assert.assertArrayEquals(new int[]{19, 31, 47, 62, 84, 96}, Sort.comparisonCountingSort(arr));
    }

    @Test
    public void testDistributionCountingSort() {
        int[] arr = {13, 11, 12, 13, 12, 12};
        Assert.assertArrayEquals(new int[]{11, 12, 12, 12, 13, 13}, Sort.distributionCountingSort(arr, 11, 13));
    }

    @Test
    public void testQuickSort() {
        int[] arr = {33, 13, 14, 94, 82, 25};
        Sort.quickSort(arr, 0, 5);
        Assert.assertArrayEquals(new int[]{13, 14, 25, 33, 82, 94}, arr);
    }

}