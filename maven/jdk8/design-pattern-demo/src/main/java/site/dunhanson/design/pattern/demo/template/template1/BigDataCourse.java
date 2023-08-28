package site.dunhanson.design.pattern.demo.template.template1;

import lombok.Getter;

@Getter
public class BigDataCourse extends NetworkCourse{
    private final Boolean needHomeworkFlag;

    public BigDataCourse(boolean needHomeworkFlag) {
        this.needHomeworkFlag = needHomeworkFlag;
    }

    @Override
    void checkHomework() {
        System.out.println("检查大数据的课后作业");
    }

    @Override
    protected boolean needHomework() {
        return getNeedHomeworkFlag();
    }
}
