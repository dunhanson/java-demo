package site.dunhanson.basic.demo.pattern.factory.simple;

import site.dunhanson.basic.demo.pattern.factory.entity.MailSender;
import site.dunhanson.basic.demo.pattern.factory.entity.Sender;
import site.dunhanson.basic.demo.pattern.factory.entity.SmsSender;

public class SimpleSendFactory {
    public Sender produce(String type) {
        if("mail".equals(type)) {
            return new MailSender();
        } else if("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型！");
            return null;
        }
    }
}
