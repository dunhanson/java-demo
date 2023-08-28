package site.dunhanson.design.pattern.demo.template.template1;

public abstract class NetworkCourse {
    // 发布预习资料
    protected final void createCourse() {
        // 1、发布预习资料
        this.postPreResource();
        // 2、制作课件PPT
        this.createPPT();
        // 3、在线直播
        this.liveVideo();
        // 4、提交课堂源笔记
        this.postNote();
        // 5、提交源码
        this.postSource();
        // 6、布置作业，有些课是没有作业的，有些课是有作业的
        // 如果有作业，检查作业，如果没有作业，流程结束
        if(this.needHomework()) {
            this.checkHomework();
        }
    }
    abstract void checkHomework();
    // 钩子方法，实现流程的微调
    protected boolean needHomework() {
        return false;
    }
    final void postSource() {
        System.out.println("提交源代码");
    }

    final void postNote() {
        System.out.println("提交课件和笔记");
    }

    final void liveVideo() {
        System.out.println("直播授课");
    }

    final void createPPT() {
        System.out.println("创建备课PPT");
    }

    public void postPreResource() {
        System.out.println("分发预习资料");
    }
}
