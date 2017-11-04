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
        int[] arr = {39, 23, 12, 77, 48, 61, 55};
        int result = Search.quickSelect(arr, 0, arr.length - 1, 4);
        System.out.println(result);
    }

}