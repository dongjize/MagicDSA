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

}