package site.dunhanson.design.pattern.demo.delegate.delegate1;

public class EmployeeA implements Employee{
    @Override
    public void doting(String command) {
        System.out.println("我是员工A，我现在开始干" + command + "工作");
    }
}
