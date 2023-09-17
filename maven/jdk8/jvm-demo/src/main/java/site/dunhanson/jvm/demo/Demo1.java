package site.dunhanson.jvm.demo;


import java.util.ArrayList;
import java.util.List;

public class Demo1 {
    byte[] array = new byte[1024 * 1024];

    public static void main(String[] args) {
        List<Demo1> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                list.add(new Demo1());
                count++;
            }
        } catch (Error e) {
            System.out.println("count:" + count);
            e.printStackTrace();
        }
    }
}
