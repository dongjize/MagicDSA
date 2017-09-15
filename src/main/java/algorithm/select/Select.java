package algorithm.select;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-06
 * @Time: 18:52
 */
public class Select {

    /**
     * quick select algorithm
     * <p>
     * Although the worst case complexity for QuickSelect is quadratic,
     * its average-case complexity is linear
     *
     * @param arr
     * @param low
     * @param high
     * @param k
     * @return
     */
    public static int quickSelect(int[] arr, int low, int high, int k) {
        int s = lomutoPartition(arr, low, high);
        if (s - low == k - 1) {
            return arr[s];
        } else if (s - low > k - 1) {
            quickSelect(arr, low, s - 1, k);
        } else {
            quickSelect(arr, s + 1, high, (k - 1) - (s - low));
        }
        return -1;
    }

    /**
     * lomuto partition algorithm
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private static int lomutoPartition(int[] arr, int low, int high) {
        //select the first element as pivot
        int pivot = arr[low];
        int split = low;
        //loop through arr -- if arr[i] < pivot, swap arr[i] with arr[s]
        for (int i = low + 1; i < high; i++) {
            if (arr[i] < pivot) {
                split++;
                swap(arr[split], arr[i]);
            }
        }
        //place pivot in the middle, larger than left and smaller than right
        swap(arr[low], arr[split]);
        return split;
    }

    private static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

}
