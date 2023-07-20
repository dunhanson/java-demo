package site.dunhanson.basic.demo.collection.array;

import java.util.Arrays;

public class ArrayExample {
    public static void main(String[] args) {
        String[] arr1 = new String[]{"aaa","bbb","ccc"};
        System.out.println(Arrays.toString(Arrays.copyOf(arr1, 4)));
    }
}
