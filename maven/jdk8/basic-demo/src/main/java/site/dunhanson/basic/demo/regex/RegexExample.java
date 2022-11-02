package site.dunhanson.basic.demo.regex;

import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        String str = "/zbxx/list_21_138_1.html";
        String regex = "/[A-Za-z]+/[A-Za-z]+_\\d+_\\d+_\\d+.html";
        System.out.println(Pattern.matches(regex, str));
    }
}
