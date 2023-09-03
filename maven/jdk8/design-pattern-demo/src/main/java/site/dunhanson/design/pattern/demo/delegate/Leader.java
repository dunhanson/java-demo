package site.dunhanson.design.pattern.demo.delegate;

import java.util.HashMap;
import java.util.Map;

public class Leader implements Employee{
    private final Map<String, Employee> targets = new HashMap<>();

    public Leader() {
        targets.put("加密", new EmployeeA());
        targets.put("登录", new EmployeeB());
    }

    @Override
    public void doting(String command) {
        targets.get(command).doting(command);
    }
}
