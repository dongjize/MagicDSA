package queue.deque;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 21:07
 */
public interface Deque<E> {
    int getSize();//返回队列中元素数目

    boolean isEmpty();//判断队列是否为空

    E first() throws RuntimeException;//取首元素（但不删除）

    E last() throws RuntimeException;//取末元素（但不删除）

    void insertFirst(E obj);//将新元素作为首元素插入

    void insertLast(E obj);//将新元素作为末元素插入

    E removeFirst() throws RuntimeException;//删除首元素

    E removeLast() throws RuntimeException;//删除末元素

    void traversal();//遍历
}
