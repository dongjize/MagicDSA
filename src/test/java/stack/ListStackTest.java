package stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ListStackTest {

    private Stack<Object> stack;

    @Before
    public void setUp() throws Exception {
        stack = new ListStack<>();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testInt() {
        long l = 100001000010000L;
        System.out.println(Long.toBinaryString(l));
        System.out.print(Long.toBinaryString(l >> 32) + ": ");
        System.out.println(l >> 32);
        System.out.print(Integer.toBinaryString((int) l) + ": ");
        System.out.println((int) l);
    }

    @Test
    public void getSize() throws Exception {
        assertNotNull(stack);
        assertEquals(stack.getSize(), 0);
        stack.push(1);
        stack.push("haha");
        assertEquals(stack.getSize(), 2);
    }

    @Test
    public void isEmpty() throws Exception {
        assertTrue(stack.isEmpty());
        stack.push("fuck");
        assertFalse(stack.isEmpty());
    }

    @Test
    public void top() throws Exception {
        stack.push("deck");
        stack.push("dick");
        stack.push("dock");
        stack.push("duck");
        assertEquals(stack.top(), "duck");
        stack.pop();
        assertEquals(stack.top(), "dock");
    }

    @Test
    public void push() throws Exception {
        stack.push("deck");
        stack.push("dick");
        assertThat("get size", stack.getSize() == 2, is(true));
        assertThat("top true", stack.top() == "dick", is(true));
        assertThat("top false", stack.top() == "deck", is(false));
    }

    @Test
    public void pop() throws Exception {
        stack.push(13);
        stack.push(24);
        stack.push(35);
        stack.push(46);
        stack.push(57);
        stack.push(68);
        stack.push(79);
        stack.push(80);
        stack.push(98);
        stack.push(99);
        while (stack.getSize() > 0) {
            System.out.println(stack.pop());
        }
        assertTrue(stack.isEmpty());
        assertTrue(stack.getSize() == 0);
    }

}