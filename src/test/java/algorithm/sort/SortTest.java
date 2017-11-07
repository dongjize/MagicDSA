package algorithm.sort;

import algorithm.Sort;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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
//        int[] arr = {1, 4, 7, 1, 5, 5, 3, 85, 34, 75, 23, 75, 2, 0};
        Sort.quickSort(arr, 0, arr.length - 1);
        Assert.assertArrayEquals(new int[]{13, 14, 25, 33, 82, 94}, arr);
//        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testShellSort() {
        int[] arr = {1, 4, 7, 1, 5, 5, 3, 85, 34, 75, 23, 75, 2, 0};
        Sort.shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}