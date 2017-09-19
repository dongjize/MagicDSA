package priorityQueue;

import sequence.Sequence;
import tree.binTree.BinTreePosition;
import tree.complBinTree.ComplBinTreeNode;
import tree.complBinTree.CompleteBinTree;
import tree.complBinTree.VectorComplBinTree;

/*
 * Description: 利用堆实现优先队列
 *
 * @Author: dong
 * @Date: 2017-09-07
 * @Time: 00:06
 */
public class HeapPQueue<K, V> implements PQueue<K, V> {

    private CompleteBinTree heap;
    private Comparator comparator;

    public HeapPQueue() {
        this(new DefaultComparator(), null);
    }

    public HeapPQueue(Comparator c) {
        this(c, null);
    }

    public HeapPQueue(Sequence s) {
        this(new DefaultComparator(), s);
    }

    /**
     * 根据某一序列直接批量式构造堆算法，s中元素都是形如(key, value)的条目
     *
     * 只需 O(n)时间，即可将 n 个条目组织为一个二叉堆结构。
     *
     * @param c
     * @param s
     */
    public HeapPQueue(Comparator c, Sequence s) {
        this.comparator = c;
        heap = new VectorComplBinTree(s);
        if (!heap.isEmpty()) {
            //自底而上，逐节点进行下滤
            for (int i = heap.getSize() / 2 - 1; i >= 0; i--) {
                percolateDown(heap.posOfNode(i));
            }
        }
    }

    @Override
    public int getSize() {
        return heap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public Entry<K, V> getMin() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("意外：优先队列为空");
        }
        return (Entry<K, V>) heap.getRoot().getElem();
    }


    /**
     * 将对象value与关键码key合成一个条目，将其插入queue中，并返回该条目
     *
     * @param key
     * @param value
     * @return
     * @throws RuntimeException
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws RuntimeException {
        checkKey(key);
        Entry<K, V> entry = new DefaultEntry<>(key, value);
        percolateUp(heap.addLast(entry));
        return entry;
    }

    /**
     * 若Q非空，则从其中摘除关键码最小的条目，并返回该条目；否则，报错
     *
     * @return
     * @throws RuntimeException
     */
    @Override
    public Entry<K, V> deleteMin() throws RuntimeException {
        if (isEmpty()) throw new RuntimeException("意外：优先队列为空");
        Entry<K, V> min = (Entry<K, V>) heap.getRoot().getElem();//保留堆顶
        if (1 == getSize())//若只剩下最后一个条目
            heap.deleteLast();//直接摘除之
        else {//否则
            heap.getRoot().setElem(((ComplBinTreeNode) heap.deleteLast()).getElem());
            //取出最后一个条目，植入堆顶
            percolateDown(heap.getRoot());
        }
        return min;//返回原堆顶
    }

    /**
     * 交换父子节点的数据
     *
     * @param u
     * @param v
     */
    protected void swapParentChild(BinTreePosition u, BinTreePosition v) {
        Object temp = u.getElem();
        u.setElem(v.getElem());
        v.setElem(temp);
    }


    /**
     * 检查关键码的可比较性
     *
     * @param key
     * @throws RuntimeException
     */
    protected void checkKey(K key) throws RuntimeException {
        try {
            comparator.compare(key, key);
        } catch (Exception e) {
            throw new RuntimeException("无法比较关键码");
        }
    }

    /**
     * 返回v的关键码
     *
     * @param v
     * @return
     */
    protected Object key(BinTreePosition v) {
        return ((Entry<K, V>) (v.getElem())).getKey();
    }


    /**
     * 上滤算法，Bottom-up
     * 不断地取当前节点的父亲，除非父亲比孩子小，否则交换父子次序，继续考察新的父节点（即原先的孩子）
     *
     * @param v
     */
    protected void percolateUp(BinTreePosition v) {
//        BinTreePosition root = heap.getRoot();
        while (v != heap.getRoot()) {
            if (comparator.compare(key(v.getParent()), key(v)) <= 0) {
                break;
            }
            swapParentChild(v.getParent(), v);
            v = v.getParent();
        }
    }


    /**
     * 下滤算法，Top-down
     *
     * @param v
     */
    protected void percolateDown(BinTreePosition v) {
        while (v.hasLChild()) {
            BinTreePosition smallerChild = v.getLChild();
            if (v.hasRChild() && comparator.compare(key(v.getLChild()), key(v.getRChild())) > 0) {
                smallerChild = v.getRChild(); //若右孩子存在且更小，则将右孩子作为进一步比较的对象
            }
            //若两个孩子都不比v更小，则下滤完成
            if (comparator.compare(key(smallerChild), key(v)) >= 0) {
                break;
            }
            //否则，将其与更小的孩子交换，并继续考察这个孩子
            swapParentChild(v, smallerChild);
            v = smallerChild;
        }
    }


}
