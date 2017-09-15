package vector;

/*
 * Description: vector.Vector implemented by array. Fixed size depending on the array's size. No good.
 *
 * @Author: dong
 * @Date: 2017-08-26
 * @Time: 14:28
 */
public class ArrayVector implements Vector {

    private final int N = 1024;
    private int n = 0;
    private Object[] a;

    public ArrayVector() {
        a = new Object[N];
        n = 0;
    }

    @Override
    public int getSize() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Object get(int index) throws RuntimeException {
        if (index < 0 || index >= n) {
            throw new RuntimeException("Exception: Out of bounds!");
        }
        return a[index];
    }

    @Override
    public Object replaceAt(int index, Object o) throws RuntimeException {
        if (index < 0 || index >= n) {
            throw new RuntimeException("Exception: Out of bounds!");
        }
        Object oldValue = a[index];
        a[index] = o;
        return oldValue;
    }

    @Override
    public void insertAt(int index, Object o) throws RuntimeException {
        if (index < 0 || index >= n) {
            throw new RuntimeException("Exception: Out of bounds!");
        }
        if (n > N) {
            throw new RuntimeException("Exception: Array overflow");
        }
        for (int i = n; i > index; i--) {
            a[i] = a[i - 1];
        }
        a[index] = o;
        n++;
    }

    @Override
    public Object removeAt(int index) throws RuntimeException {
        if (index < 0 || index >= n) {
            throw new RuntimeException("Exception: Out of bounds!");
        }
        if (n > N) {
            throw new RuntimeException("Exception: Array overflow");
        }
        Object oldValue = a[index];
        for (int i = index; i < n - 1; i++) {
            a[i] = a[i + 1];
        }
        n--;
        return oldValue;
    }
}
