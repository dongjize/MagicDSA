package algorithm.sort;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-23
 * @Time: 13:27
 */
public class SortClient {
    public static void main(String[] args) {
//        int[] arr = {94, 33, 82, 25, 59, 94, 65, 23, 45, 13, 14, 27, 73, 25, 39, 10};
        int[] arr1 = {33, 13, 14, 94, 82, 25};
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64,
                5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
//        int[] result1 = sortSolution.insertionSort(arr);
//        int[] result2 = sortSolution.selectionSort(arr1);
        Sort.quickSort(arr, 0, arr.length - 1);
//        Sort.mergeSort(arr);


//        int[] longArray = randomArray(500);
//        Sort.shellSort(arr);

    }

    private static int[] randomArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10000);
        }
        return arr;
    }
}

