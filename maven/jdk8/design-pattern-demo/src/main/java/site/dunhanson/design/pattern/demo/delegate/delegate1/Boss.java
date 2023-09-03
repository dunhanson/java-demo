package site.dunhanson.design.pattern.demo.delegate.delegate1;

public class Boss {
    public void command(String command, Leader leader) {
        leader.doting(command);
    }
}
