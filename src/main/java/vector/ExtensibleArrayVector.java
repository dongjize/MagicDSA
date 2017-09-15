package vector;

/*
 * Description:
 * Vector implemented by an extensible array, which possesses a fixed length array,
 * but can be extended if inserting an excessive number of elements by constantly doubling
 * the array's length.
 *
 * @Author: dong
 * @Date: 2017-08-26
 * @Time: 14:44
 */
public class ExtensibleArrayVector implements Vector {
    private int N = 8;
    private int size; //向量的实际规模
    private Object[] arr;

    public ExtensibleArrayVector() {
        arr = new Object[N];
        size = 0;
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
    public Object get(int index) throws RuntimeException {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Exception: Out of bounds!");
        }
        return arr[index];
    }

    @Override
    public Object replaceAt(int index, Object e) throws RuntimeException {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Exception: Out of bounds!");
        }
        Object oldValue = arr[index];
        arr[index] = e;
        return oldValue;
    }

    @Override
    public void insertAt(int index, Object obj) throws RuntimeException {
        if (index < 0 || index > size) {
            throw new RuntimeException("Exception: Out of bounds!");
        }
        if (size >= N) {
            N *= 2;
            Object[] b = new Object[N];
            for (int i = 0; i < size; i++) {
                b[i] = arr[i]; //A[]中内容复制至B[]
            }
            arr = b; //用B替换A（原A[]将被自动回收）
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = obj;
        size++;
    }

    @Override
    public Object removeAt(int index) throws RuntimeException {
        if (index < 0 || index >= size) {
            throw new RuntimeException("Exception: Out of bounds!");
        }
        if (size > N) {
            throw new RuntimeException("Exception: Array overflow");
        }
        Object oldValue = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return oldValue;
    }

}
