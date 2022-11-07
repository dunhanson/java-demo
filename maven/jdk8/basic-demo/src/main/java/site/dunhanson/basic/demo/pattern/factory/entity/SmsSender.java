package site.dunhanson.basic.demo.pattern.factory.entity;

public class SmsSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is SmsSender");
    }
}
