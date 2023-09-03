package site.dunhanson.design.pattern.demo.delegate;

public class DelegateTest {
    public static void main(String[] args) {
        new Boss().command("登录", new Leader());
    }
}
