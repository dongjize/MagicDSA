package map;

import base.Iterator;
import list.DLNodeList;
import list.ElementIterator;
import list.List;
import priorityQueue.Entry;

/*
 * Description: 基于散列表实现的（无序）词典结构，采用分离链策略解决冲突
 *
 * @Author: dong
 * @Date: 2017-09-20
 * @Time: 14:45
 */
public class HashTableDictionary<K, V> implements Dictionary<K, V> {

    private Dictionary<K, V>[] bucketArray; //桶数组，每个桶本身也是一个（基于列表实现的）词典结构
    private int N;
    private final double maxLambda = 0.75; //装填因子上限
    private int size; //词典结构的规模
    private EqualityTester tester;

    public HashTableDictionary() {
        this(0, new DefaultEqualityTester());
    }

    public HashTableDictionary(int n, EqualityTester t) {
        this.tester = t;
        N = p(n);
        bucketArray = new Dictionary[N];
        for (int i = 0; i < N; i++) {
            bucketArray[i] = new ListDictionary<>(tester);
        }
        size = 0;
    }


    /**
     * 散列定址函数（采用模余法）
     *
     * @param key
     * @return
     */
    private int hash(Object key) {
        return key.hashCode() % N;
    }

    /**
     * 判断n是否为素数
     *
     * @param n
     * @return
     */
    private static boolean prime(int n) {
        for (int i = 3; i < 1 + Math.sqrt(n); i++) {
            if (n / i * i == n) {
                return false;
            }
        }
        return true;
    }

    /**
     * 取不小于n的最小素数
     *
     * @param n
     * @return
     */
    private static int p(int n) {
        if (n < 3) {
            n = 3;
        }
        n = n | 1; //奇数化
        while (!prime(n)) n += 2;
        return n;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Entry<K, V> find(Object key) {
        return bucketArray[hash(key)].find(key);
    }

    @Override
    public Iterator<Entry<K, V>> findAll(Object key) {
        return bucketArray[hash(key)].findAll(key);
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> entry = bucketArray[hash(key)].insert(key, value);
        size++;
        if (size > N * maxLambda) {
            rehash();
        }
        return entry;
    }

    @Override
    public Entry<K, V> remove(Object key) {
        Entry<K, V> oldEntry = bucketArray[hash(key)].remove(key);
        if (oldEntry != null) {
            size--;
        }
        return oldEntry;
    }

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

    private void rehash() {
        Iterator it = this.iterator();
        N = p(N << 1);
        bucketArray = new Dictionary[N];
        for (int i = 0; i < N; i++) {
            bucketArray[i] = new ListDictionary<>(tester);
        }
        while (it.hasNext()) {
            Entry<K, V> e = (Entry<K, V>) it.getNext();
            K k = e.getKey();
            V v = e.getValue();
            bucketArray[hash(k)].insert(k, v);
        }
    }
}
