package map;

/*
 * Description: 判等器接口
 *
 * @Author: dong
 * @Date: 2017-09-07
 * @Time: 22:14
 */
public interface EqualityTester<T> {
    /**
     * 相等返回true，否则false
     * @param a
     * @param b
     * @return
     */
    boolean equals(T a, T b);
}
