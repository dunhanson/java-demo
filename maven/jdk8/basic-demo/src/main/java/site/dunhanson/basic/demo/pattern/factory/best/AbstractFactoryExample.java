package site.dunhanson.basic.demo.pattern.factory.best;

import site.dunhanson.basic.demo.pattern.factory.entity.MailSender;
import site.dunhanson.basic.demo.pattern.factory.entity.Sender;
import site.dunhanson.basic.demo.pattern.factory.entity.SmsSender;

public class AbstractFactoryExample {
    interface Provider {
        Sender produce();
    }

    static class SendMailFactory implements Provider {
        @Override
        public Sender produce() {
            return new MailSender();
        }
    }

    static class SendSmsFactory implements Provider {

        @Override
        public Sender produce() {
            return new SmsSender();
        }
    }

    public static void main(String[] args) {
        // 1
        Provider provider = new SendMailFactory();
        Sender sender = provider.produce();
        sender.send();
        // 2
        provider = new SendSmsFactory();
        sender = provider.produce();
        sender.send();
    }

}
