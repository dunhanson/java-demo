package site.dunhanson.basic.demo.str;

public class HashCodeStrExample {
    public static void main(String[] args) {
        String str1 = "我是谁";
        String str2 = "谁是我";
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
    }
}
