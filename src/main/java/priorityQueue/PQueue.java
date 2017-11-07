package priorityQueue;

/**
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-02
 * @Time: 15:55
 */
public interface PQueue<K, V> {
    /**
     * 统计优先队列的规模
     *
     * @return
     */
    int getSize();

    /**
     * 判断优先队列是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 若Q非空，则返回其中的最小条目（并不删除）;否则，报错
     *
     * @return
     * @throws RuntimeException
     */
    Entry<K, V> getMin() throws RuntimeException;

    /**
     * 将value与关键码k合成一个条目，将其插入Q中，并返回该条目
     *
     * @param key
     * @param value
     * @return
     * @throws RuntimeException
     */
    Entry<K, V> insert(K key, V value) throws RuntimeException;

    /**
     * 若Q非空，则从其中摘除关键码最小的条目，并返回该条目；否则，报错
     *
     * @return
     * @throws RuntimeException
     */
    Entry<K, V> deleteMin() throws RuntimeException;
}
