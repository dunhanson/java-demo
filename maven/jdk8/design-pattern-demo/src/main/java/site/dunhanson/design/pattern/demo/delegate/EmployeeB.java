package site.dunhanson.design.pattern.demo.delegate;

public class EmployeeB implements Employee{
    @Override
    public void doting(String command) {
        System.out.println("我是员工B，我现在开始干" + command + "工作");
    }
}
