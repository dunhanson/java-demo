package site.dunhanson.design.pattern.demo.proxy.proxy2;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


@AllArgsConstructor
public class MyProxy implements InvocationHandler {
    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before calling method");
        Object result = method.invoke(target, args);
        System.out.println("After calling method");
        return result;
    }
}
