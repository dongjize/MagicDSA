package priorityQueue;

/**
 * The interface Comparator.
 *
 * @param <T> the type parameter
 */
/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-02
 * @Time: 15:35
 */
public interface Comparator<T> {
    /**
     * compare t1 with t2
     *
     * @param t1 the t 1
     * @param t2 the t 2
     * @return If t1 > t2, return positive; if t1 < t2, return negative; else return 0.
     */
    int compare(T t1, T t2);
}
