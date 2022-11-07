package site.dunhanson.basic.demo.pattern.factory.method;

import site.dunhanson.basic.demo.pattern.factory.entity.MailSender;
import site.dunhanson.basic.demo.pattern.factory.entity.Sender;
import site.dunhanson.basic.demo.pattern.factory.entity.SmsSender;

public class MethodSendFactory {
    public Sender produceSms() {
        return new SmsSender();
    }

    public Sender produceMail() {
        return new MailSender();
    }
}
