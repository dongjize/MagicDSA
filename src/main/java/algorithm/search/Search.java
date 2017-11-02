package algorithm.search;

/*
 * Description: A set of searching algorithms
 *
 * @Author: dong
 * @Date: 2017-08-24
 * @Time: 19:02
 */
public class Search {

    /**
     * Sequential Search, using brute-force
     *
     * @param arr    arbitrary array
     * @param target searching target
     * @return the index that matches the target
     */
    public static int sequentialSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (target == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Binary Search
     * Complexity: O(log n)
     *
     * @param arr    sorted array
     * @param target searching target
     * @return the index that matches the target
     */
    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 差值查找 Interpolation Search
     *
     * @param arr    sorted array
     * @param target searching target
     * @return the index that matches the target
     */
    public static int interpolationSearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int x = low + (target - arr[low]) * (high - low) / (arr[high] - arr[low]);
            if (arr[x] > target) {
                high = x - 1;
            } else if (arr[x] < target) {
                low = x + 1;
            } else {
                return x;
            }
        }
        return -1;
    }
}