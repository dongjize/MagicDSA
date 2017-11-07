package tree.searchTree;

import map.Dictionary;
import priorityQueue.Comparator;
import priorityQueue.Entry;
import tree.binTree.BinTreePosition;

/**
 * Description: AVL Tree
 *
 * @Author: dong
 * @Date: 2017-09-14
 * @Time: 13:27
 */
public class AVLTree<E> extends BSTree<E> implements Dictionary {
    public AVLTree() {
        super();
    }

    public AVLTree(BinTreePosition<E> r) {
        super(r);
    }

    public AVLTree(BinTreePosition<E> r, Comparator c) {
        super(r, c);
    }


    /**
     * 插入条目(key, value)，并返回该条目
     * <p>
     * AVL 树的节点插入操作可以在 O(logn)时间内完成。
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Entry insert(Object key, Object value) {
        Entry e = super.insert(key, value);
        root = rebalance(lastV.getParent(), root);
        return e;
    }

    /**
     * 若词典中存在以key为关键码的条目，则将摘除其中的一个并返回；否则，返回null
     *
     * @param key
     * @return
     */
    @Override
    public Entry remove(Object key) {
        Entry e = super.remove(key);
        if (e != null) {
            root = rebalance(lastV, root);
        }
        return e;
    }


    /**
     * 判断节点v是否平衡（左右子树高度差绝对值不大于1）
     *
     * @param v
     * @return
     */
    protected boolean isBalanced(BinTreePosition<E> v) {
        if (v == null) {
            return true;
        }
        int lH = v.hasLChild() ? v.getLChild().getHeight() : -1;
        int rH = v.hasRChild() ? v.getRChild().getHeight() : -1;
        int deltaH = lH - rH;
        return deltaH >= -1 && deltaH <= 1;
    }


    protected BinTreePosition<E> rebalance(BinTreePosition<E> z, BinTreePosition<E> r) {
        if (z == null) {
            return r;
        }
        //从z开始，向上逐一检查z的祖先
        while (true) {
            //若z节点失去平衡，则通过旋转使之重新平衡
            if (!isBalanced(z)) {
                rotate(z);
            }
            if (!z.hasParent()) {
                return z;
            }
            z = z.getParent(); //继续检查其父亲
        }
    }

    /**
     * 通过旋转，使节点z的平衡因子的绝对值不超过1（支持AVL树）
     * <p>
     * 两次单旋分别只需O(logn)时间。因此，整个双旋调整过程只需O(logn)时间
     * <p>
     * 在 AVL 树中插入一个节点后，至多只需经过两次旋转即可使之恢复平衡
     *
     * @param z
     * @return 返回新的子树根
     */
    public BinTreePosition<E> rotate(BinTreePosition<E> z) {
        BinTreePosition<E> y = tallerChild(z); //取y为z更高的孩子
        BinTreePosition<E> x = tallerChild(y); //取x为y更高的孩子
        boolean cType = z.isLChild();
        BinTreePosition<E> p = z.getParent(); //p为z的父亲
        BinTreePosition<E> a, b, c; //自左向右，三个节点
        BinTreePosition<E> tree0, tree1, tree2, tree3; //自左向右，四棵子树

        /******** 以下分四种情况 ********/

        if (y.isLChild()) { //若y是左孩子，则
            c = z;
            tree3 = z.getRChild();
            if (x.isLChild()) { //若x是左孩子
                b = y;
                tree2 = y.getRChild();
                a = x;
                tree1 = x.getRChild();
                tree0 = x.getLChild();
            } else {//若x是右孩子
                a = y;
                tree0 = y.getLChild();
                b = x;
                tree1 = x.getLChild();
                tree2 = x.getRChild();
            }
        } else { //若y是右孩子，则
            a = z;
            tree0 = z.getLChild();
            if (x.isRChild()) { //若x是右孩子
                b = y;
                tree1 = y.getLChild();
                c = x;
                tree2 = x.getLChild();
                tree3 = x.getRChild();
            } else { //若x是左孩子
                c = y;
                tree3 = y.getRChild();
                b = x;
                tree1 = x.getLChild();
                tree2 = x.getRChild();
            }
        }
        //摘下三个节点
        z.secede();
        y.secede();
        x.secede();
        //摘下四棵子树
        if (null != tree0) {
            tree0.secede();
        }
        if (null != tree1) {
            tree1.secede();
        }
        if (null != tree2) {
            tree2.secede();
        }
        if (null != tree3) {
            tree3.secede();
        }
        //重新链接
        a.attachL(tree0);
        a.attachR(tree1);
        b.attachL(a);
        c.attachL(tree2);
        c.attachR(tree3);
        b.attachR(c);
        //子树重新接入原树
        if (null != p)
            if (cType) {
                p.attachL(b);
            } else {
                p.attachR(b);
            }
        return b; //返回新的子树根
    }


    protected BinTreePosition<E> tallerChild(BinTreePosition<E> v) {
        int lH = v.hasLChild() ? v.getLChild().getHeight() : -1;
        int rH = v.hasRChild() ? v.getRChild().getHeight() : -1;
        if (lH > rH) {
            return v.getLChild();
        }
        if (lH < rH) {
            return v.getRChild();
        }
        return v.isLChild() ? v.getLChild() : v.getRChild();
    }


    protected BinTreePosition<E> shorterChild(BinTreePosition<E> v) {
        int lH = v.hasLChild() ? v.getLChild().getHeight() : -1;
        int rH = v.hasRChild() ? v.getRChild().getHeight() : -1;
        if (lH > rH) {
            return v.getRChild();
        }
        if (lH < rH) {
            return v.getLChild();
        }
        return v.isLChild() ? v.getRChild() : v.getLChild();
    }


}
