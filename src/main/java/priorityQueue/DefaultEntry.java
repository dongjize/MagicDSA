package priorityQueue;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-02
 * @Time: 15:27
 */
public class DefaultEntry<K, V> implements Entry<K, V> {
    protected K key;
    protected V value;

    public DefaultEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public void setKey(K k) {
        key = k;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void setValue(V v) {
        value = v;
    }
}
