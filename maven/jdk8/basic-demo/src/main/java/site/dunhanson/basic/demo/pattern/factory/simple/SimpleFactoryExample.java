package site.dunhanson.basic.demo.pattern.factory.simple;

import site.dunhanson.basic.demo.pattern.factory.entity.Sender;

public class SimpleFactoryExample {
    public static void main(String[] args) {
        Sender sender = new SimpleSendFactory().produce("sms");
        sender.send();
    }
}
