package site.dunhanson.basic.demo.pattern.factory.entity;

public class MailSender implements Sender{
    @Override
    public void send() {
        System.out.println("this is MailSender");
    }
}
