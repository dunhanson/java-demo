package site.dunhanson.basic.demo.pattern.factory.method;

public class MethodFactoryExample {
    public static void main(String[] args) {
        MethodSendFactory factory = new MethodSendFactory();
        factory.produceSms().send();
        factory.produceMail().send();
    }
}
