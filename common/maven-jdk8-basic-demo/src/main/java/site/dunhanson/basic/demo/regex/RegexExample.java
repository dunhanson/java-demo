package site.dunhanson.basic.demo.regex;

import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        RegexExample example = new RegexExample();
        example.test4();
    }

    public void test1() {
        String str = "/zbxx/list_21_138_1.html";
        String regex = "/[A-Za-z]+/[A-Za-z]+_\\d+_\\d+_\\d+.html";
        System.out.println(Pattern.matches(regex, str));
    }

    /**
     * 手机号码
     */
    public void test2() {
        String str = "12139878908";
        String regex = "^1[3-9]\\d{9}$";
        System.out.println(Pattern.matches(regex, str));
    }

    /**
     * 日期
     */
    public void test3() {
        String str1 = "2022-1２-0１";
        String str2 = "2022-12-01";
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        System.out.println(Pattern.matches(regex, str1));
        System.out.println(Pattern.matches(regex, str2));
    }

    /**
     * 日期
     */
    public void test4() {
        String str1 = "2022-1２-0１ 00:00:00";
        String str2 = "2022-12-01 00:00:00";
        String regex = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        System.out.println(Pattern.matches(regex, str1));
        System.out.println(Pattern.matches(regex, str2));
    }


}
