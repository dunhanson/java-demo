package site.dunhanson.design.pattern.demo.observer.observer1;

import lombok.Getter;

import java.util.Observable;

@Getter
public class GPer extends Observable {
    private final String name = "GPer生态圈";
    private static GPer gper = null;
    private GPer() {}

    public static GPer getInstance() {
        if(null == gper) {
            gper = new GPer();
        }
        return gper;
    }

    public void publishQuestion(Question question) {
        System.out.println(question.getUserName() + "在" + this.name + "提交了一个问题。");
        this.setChanged();
        this.notifyObservers(question);
    }
}
