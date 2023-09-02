package site.dunhanson.design.pattern.demo.observer.observer1;

public class ObserverTest {
    public static void main(String[] args) {
        GPer gPer = GPer.getInstance();
        Teacher tom = new Teacher("Tom");
        Teacher mic = new Teacher("Mic");

        gPer.addObserver(tom);
        gPer.addObserver(mic);

        Question question = new Question();
        question.setUserName("小明");
        question.setContent("观察者设计模式适用于哪些场景？");

        gPer.publishQuestion(question);
    }
}