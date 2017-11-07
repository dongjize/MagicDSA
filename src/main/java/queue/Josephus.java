package queue;

/**
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-08-19
 * @Time: 17:02
 */
public class Josephus {
    //利用队列结构模拟Josephus环
    public static Object josephus(Queue queue, int k) {
        if (queue.isEmpty()) {
            return null;
        }
        while (queue.getSize() > 1) {//不断迭代
            queue.traversal();//显示当前的环
            for (int i = 0; i < k; i++){
                //将山芋向前传递k次
                queue.enqueue(queue.dequeue());
            }
            Object e = queue.dequeue();//拿着山芋的孩子退出
            System.out.println("\n\t" + e + "退出");
        }
        return queue.dequeue();//最后剩下的那个孩子
    }

    //将一组对象组织为一个队列
    public static Queue buildQueue(Object a[]) {
        Queue queue = new ArrayQueue();
        for (int i = 0; i < a.length; i++){
            queue.enqueue(a[i]);
        }
        return queue;
    }

    //测试用main方法
    public static void main(String[] args) {
        String[] kids = {
                "Alice", "Bob", "Cindy", "Doug", "Ed",
                "Fred", "Gene", "Hope", "Irene", "Jack",
                "Kim", "Lance", "Mike", "Nancy", "Ollie"
        };
        System.out.println("最终的幸运者是" + josephus(buildQueue(kids), 5));
    }
}
