package algorithm.sort;

import java.util.Arrays;

/*
 * Description: A set of sorting algorithms
 * - CountingSort
 * - BubbleSort
 * - SelectionSort
 * - InsertionSort
 * - ShellSort
 * - MergeSort
 * - QuickSort
 *
 * @Author: dong
 * @Date: 2017-08-30
 * @Time: 20:57
 */
public class Sort {

    /**
     * 计数排序 ComparisonCountingSort
     * Time Efficiency: n(n-1)/2
     *
     * @param arr
     * @return
     */
    public static int[] comparisonCountingSort(int arr[]) {
        int n = arr.length;
        int[] counts = new int[n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] < arr[j]) {
                    counts[j]++;
                } else {
                    counts[i]++;
                }
            }
        }
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[counts[i]] = arr[i];
        }
        return s;
    }


    /**
     * 分布计数排序 DistributionCountingSort
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int[] distributionCountingSort(int[] arr, int low, int high) {
        int n = arr.length;
        int[] s = new int[arr.length];
        int[] d = new int[high - low + 1];
        for (int i = 0; i < n; i++) {
            d[arr[i] - low] = d[arr[i] - low] + 1;
        }
        for (int j = 1; j < high - low + 1; j++) {
            d[j] = d[j - 1] + d[j];
        }
        for (int i = n - 1; i > -1; i--) {
            int j = arr[i] - low;
            s[d[j] - 1] = arr[i];
            d[j]--;
        }
        return s;
    }


    /**
     * 冒泡排序 BubbleSort
     * Complexity: O(n^2)
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 快速排序 QuickSort
     * 分治法 Divide and Conquer
     * <p>
     * Unstable:
     * The worst case requires a comparison number of Θ(n^2)
     * The average case belongs to O(nlog(n))
     * <p>
     * In-place
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort(int arr[], int left, int right) {
        int split;
        if (left < right) {
            split = hoarePartition(arr, left, right);
            quickSort(arr, left, split - 1);
            quickSort(arr, split + 1, right);
        }
    }

    /**
     * Partitions a sub-array by Hoare's algorithm, using the first element as a pivot
     * Complexity: O(n)
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int hoarePartition(int arr[], int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            if (left < right) {
                arr[left++] = arr[right];
            }
            while (left < right && arr[left] < pivot) {
                left++;
            }
            if (left < right) {
                arr[right--] = arr[left];
            }
        }
        arr[left] = pivot;
        return left;
    }


    /**
     * 归并排序 Merge Sort
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        int[] arr1 = new int[arr.length];
        int[] arr2 = new int[arr.length];
        if (arr.length > 0) {
            copy(arr, 0, arr.length / 2 - 1, arr1, 0, arr.length / 2 - 1);
            copy(arr, arr.length / 2, arr.length - 1, arr2, 0, arr.length / 2 - 1);
            arr1 = Arrays.copyOf(arr1, arr.length / 2 - 1);
            arr2 = Arrays.copyOf(arr2, arr.length / 2 - 1);
            mergeSort(arr1);
            mergeSort(arr2);
            merge(arr1, arr2, arr);
        }
    }

    private static void copy(int[] arr1, int start1, int end1, int[] arr2, int start2, int end2) {
        for (int i = 0; i < end1 - start1 + 1; i++) {
            arr2[start2 + i] = arr1[start1 + i];
        }
    }

    private static void merge(int[] B, int[] C, int[] A) {
        int i = 0, j = 0, k = 0;
        while (i < B.length && j < C.length) {
            if (B[i] < C[j]) {
                A[k] = B[i++];
            } else {
                A[k] = C[j++];
            }
            k++;
        }
        if (i == B.length) {
            copy(C, j, C.length - 1, A, k, B.length + C.length - 1);
        } else {
            copy(B, i, B.length - 1, A, k, B.length + C.length - 1);
        }
    }

    /**
     * 选择排序 Selection Sort
     *
     * @param arr
     * @return
     */
    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;
    }


    /**
     * 插入排序 InsertionSort
     * Complexity: O(n^2)
     * A[0] ≤ ... ≤ A[j] < A[j + 1] ≤ ... ≤ A[i – 1], A[i] ... A[n – 1]
     * |_____________________________|
     *
     * @param arr
     * @return
     */
    public static void insertionSort(int[] arr) {
//        for (int i = 1; i < arr.length; i++) {
//            int v = arr[i];
//            int j = i - 1;
//            while (j >= 0 && arr[j] > v) {
//                arr[j + 1] = arr[j];
//                j--;
//            }
//            arr[j + 1] = v;
//        }
//        return arr;

        //调用可选择步长的插入排序，步数为1
        insertionSort(arr, 1);
    }

    /**
     * 可选择步长的插入排序
     *
     * @param arr
     * @param gap
     */
    public static void insertionSort(int[] arr, int gap) {
        for (int i = gap; i < arr.length; i++) {
            int j = i - gap;
            int v = arr[i];
            while (j >= 0 && arr[j] > v) {
                arr[j + gap] = arr[j];
                j -= gap;
            }
            arr[j + gap] = v;
        }
    }

    /**
     * 希尔排序
     *
     * @param arr
     * @return
     */
    public static void shellSort(int[] arr) {
        int gap = 1;
        while (gap < arr.length / 3) {
            gap = gap * 3 + 1;
        }
        for (; gap > 0; gap /= 3) {
            //Insertion Sort starts here:
            insertionSort(arr, gap);
        }
    }

}
