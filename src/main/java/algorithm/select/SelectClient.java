package algorithm.select;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-06
 * @Time: 19:26
 */
public class SelectClient {
    public static void main(String[] args) {
        int[] arr = {39, 23, 12, 77, 48, 61, 55};
        int result = Select.quickSelect(arr, 0, arr.length - 1, 4);
        System.out.println(result);
    }
}
