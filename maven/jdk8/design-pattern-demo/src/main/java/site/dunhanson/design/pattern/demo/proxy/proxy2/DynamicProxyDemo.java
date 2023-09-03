package site.dunhanson.design.pattern.demo.proxy.proxy2;

import java.lang.reflect.Proxy;

public class DynamicProxyDemo {
    public static void main(String[] args) {
        MyInterface obj = new MyClass();
        MyInterface proxy = (MyInterface) Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new MyProxy(obj));
        proxy.doSomething();
    }
}
