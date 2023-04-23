package demo;

import org.junit.Test;

public class DemoTest {
    @Test
    public void test() {
        System.out.println("hello".hashCode());
        System.out.println(new Integer(1).hashCode());
        System.out.println(new Integer(1).hashCode());
    }
}
