package priorityQueue;

/**
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-02
 * @Time: 15:39
 */
public class DefaultComparator<T> implements Comparator<T> {
    @Override
    public int compare(T t1, T t2) {
        return ((Comparable<T>) t1).compareTo(t2);
    }
}
