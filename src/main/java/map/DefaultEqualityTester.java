package map;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-07
 * @Time: 22:15
 */
public class DefaultEqualityTester<T> implements EqualityTester<T> {

    @Override
    public boolean equals(T a, T b) {
        return a.equals(b);
    }
}
