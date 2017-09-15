package stack;

/*
 * Description: 
 *
 * @Author: dong
 * @Date: 2017-09-02
 * @Time: 14:05
 */
public class StackClient {
    public static void main(String[] args) {
        ListStack listStack = new ListStack();
        listStack.push(13);
        listStack.push(24);
        listStack.push(35);
        listStack.push(46);
        listStack.push(57);
        listStack.push(68);
        listStack.push(79);
        listStack.push(80);
        listStack.push(98);
        listStack.push(99);

        System.out.println(listStack.getSize());
        System.out.println(listStack.pop());
        System.out.println(listStack.top());

    }
}
