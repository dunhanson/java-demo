package site.dunhanson.design.pattern.demo.adapter.adapter1;

public class ObjectAdapterTest {
    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter(new AC220());
        dc5.outputDC5V();
    }
}
