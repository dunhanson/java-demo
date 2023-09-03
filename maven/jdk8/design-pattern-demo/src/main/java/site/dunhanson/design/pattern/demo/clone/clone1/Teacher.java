package site.dunhanson.design.pattern.demo.clone.clone1;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Teacher implements Cloneable{
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public Object shallowClone() throws CloneNotSupportedException {
        return super.clone();
    }
}
