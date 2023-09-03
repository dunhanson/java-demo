package site.dunhanson.design.pattern.demo.clone.clone1;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Student implements Cloneable{
    private String name;
    private Teacher teacher;

    public Object shallowClone() throws CloneNotSupportedException {
        return this.clone();
    }

    public Object deepClone() throws CloneNotSupportedException {
        Teacher clonedTeacher = (Teacher) this.teacher.shallowClone();
        return new Student(this.name, clonedTeacher);
    }
}
