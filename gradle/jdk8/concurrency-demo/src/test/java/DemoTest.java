import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class DemoTest {
    @Test
    public void test() {
        long millis = System.currentTimeMillis();
        System.out.println("millis:" + millis);
        System.out.println(TimeUnit.MILLISECONDS.convert(System.currentTimeMillis() + 1000 - System.currentTimeMillis(), TimeUnit.MILLISECONDS));
    }
}
