package map;

import base.Iterator;
import list.DLNodeList;
import list.ElementIterator;
import list.List;
import priorityQueue.Entry;

/*
 * Description: 基于散列表实现的映射结构，采用分离链策略解决冲突
 *
 * @Author: dong
 * @Date: 2017-09-08
 * @Time: 00:25
 */
public class HashTableMap<K, V> implements Map<K, V> {
    private Map[] bucketArray; //桶数组，每个桶本身也是一个（基于列表实现的）映射结构
    private int N; //散列表长
    private final double maxLambda = 0.75; //装填因子上限
    private int size; //映射结构的规模
    private EqualityTester tester; //判等器

    public HashTableMap() {
        this(0, new DefaultEqualityTester());
    }

    public HashTableMap(int n, EqualityTester tester) {
        this.tester = tester;
        this.N = getPrimeNumber(n);
        this.bucketArray = new Map[N];
        for (int i = 0; i < N; i++) {
            bucketArray[i] = new ListMap(tester);
            size = 0;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 若M中存在以key为关键码的条目，则返回该条目的数据对象；否则，返回null
     *
     * @param key
     * @return
     */
    @Override
    public V get(Object key) {
        return (V) bucketArray[hash(key)].get(key);
    }

    @Override
    public V put(K key, V value) {
        V oldValue = (V) bucketArray[hash(key)].put(key, value);
        if (oldValue == null) {
            size++;
            if (size > N * maxLambda) {
                rehash();
            }
        }
        return oldValue;
    }

    @Override
    public V remove(Object key) {
        V oldValue = (V) bucketArray[hash(key)].remove(key);
        if (oldValue != null) {
            size--;
        }
        return oldValue;
    }


    /**
     * 返回map中所有条目
     * 将各桶对应的映射结构的迭代器串接起来，构成整体的迭代器
     *
     * @return
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        List list = new DLNodeList();
        for (int i = 0; i < N; i++) {
            Iterator it = bucketArray[i].iterator();
            while (it.hasNext()) {
                list.insertLast(it.getNext());
            }
        }
        return new ElementIterator<>(list);
    }


    /**
     * 散列地址函数（采用模余法）
     *
     * @param key
     * @return
     */
    private int hash(Object key) {
        return key.hashCode() % N;
    }

    /**
     * 重散列``
     */
    private void rehash() {
        Iterator it = this.iterator();
        N = getPrimeNumber(N << 1); //桶数组容量至少加倍
        bucketArray = new Map[N];
        for (int i = 0; i < N; i++) { //为每个桶分配一个子映射
            bucketArray[i] = new ListMap(tester);
        }
        //将其对应的映射结构中的各条目逐一取出，将其关键码和数据对象整合为新的条目，插入对应的子映射中
        while (it.hasNext()) {
            Entry<K, V> e = (Entry<K, V>) it.getNext();
            K k = e.getKey();
            V v = e.getValue();
            bucketArray[hash(k)].put(k, v);
        }
    }

    /**
     * Judge if n is prime number
     *
     * @param n
     * @return
     */
    private static boolean isPrime(int n) {
        for (int i = 2; i < 1 + Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the minimal prime number that's no larger than n
     *
     * @param n
     * @return
     */
    private static int getPrimeNumber(int n) {
        if (n < 2) {
            n = 2;
        } else if (n < 3) {
            n = 3;
        }
        n = n | 1;//奇数化
        while (!isPrime(n)) {
            n += 2;
        }
        return n;
    }
}
