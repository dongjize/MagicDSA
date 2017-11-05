package algorithm.search;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchTest {

    @Test
    public void binarySearchTest() {
        int[] arr = {1, 3, 55, 89, 98, 130, 255, 356, 888};
        int ans = Search.binarySearch(arr, 98);
        Assert.assertEquals(ans, 4);
    }


    @Test
    public void quickSelectTest() {
        int[] arr = {4, 1, 10, 8, 7, 12, 9, 2, 15};
        Assert.assertEquals(Search.quickSelect(arr, 0, arr.length - 1, 5), 8);
        int[] arr1 = {4, 1, 10, 8, 7, 12, 9, 2, 15};
        Assert.assertEquals(Search.quickSelect(arr1, 0, arr1.length - 1, 4), 7);
    }

}