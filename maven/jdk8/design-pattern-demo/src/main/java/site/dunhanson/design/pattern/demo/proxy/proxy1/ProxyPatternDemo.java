package site.dunhanson.design.pattern.demo.proxy.proxy1;

public class ProxyPatternDemo {
    public static void main(String[] args) {
        Shape circle = new Circle();
        CircleProxy circleProxy = new CircleProxy(circle);
        circleProxy.draw();
    }
}
