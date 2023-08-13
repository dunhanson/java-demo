package site.dunhanson.spring.boot.transaction;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class T2ReadCommittedTest {
    @Test
    public void test() {
        log.info("T2ReadCommittedTest");
    }
}
