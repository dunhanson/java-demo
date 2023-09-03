package site.dunhanson.design.pattern.demo.clone.clone1;

public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher teacher = new Teacher("王老师");
        Student student = new Student("小明", teacher);
        Student shallowClone = (Student) student.shallowClone();
        Student deepClone = (Student) student.deepClone();

        System.out.println(shallowClone);
        System.out.println(deepClone);
    }
}
