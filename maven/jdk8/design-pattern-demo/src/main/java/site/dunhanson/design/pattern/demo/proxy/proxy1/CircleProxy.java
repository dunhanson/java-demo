package site.dunhanson.design.pattern.demo.proxy.proxy1;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CircleProxy implements Shape{
    private Shape circle;

    @Override
    public void draw() {
        System.out.println("Drawing Circle before");
        circle.draw();
        System.out.println("Drawing Circle after");
    }
}
