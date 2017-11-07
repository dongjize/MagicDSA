package priorityQueue;

/**
 * Description: 条目Entry接口
 *
 * @Author: dong
 * @Date: 2017-09-02
 * @Time: 15:22
 */
public interface Entry<K, V> {
    /**
     * 取条目的关键码
     *
     * @return
     */
    K getKey();

    /**
     * 修改条目关键码
     *
     * @param k
     */
    void setKey(K k);

    /**
     * 取条目数据对象
     *
     * @return
     */
    V getValue();

    /**
     * 修改条目数据对象
     *
     * @param v
     */
    void setValue(V v);

}
