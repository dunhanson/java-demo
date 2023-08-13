package site.dunhanson.spring.boot.transaction;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import site.dunhanson.spring.boot.transaction.model.entity.UserEntity;
import site.dunhanson.spring.boot.transaction.service.intf.UserService;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@SpringBootTest
public class T3RepeatableReadTest {
    @Resource
    private UserService userService;
    /**
     * 在该级别下，一个事务在执行期间多次读取同一数据时，保证能够读取到一致的结果。
     * 即使其他事务对该数据进行修改，也不会影响当前事务的读取操作。
     * 这个级别通过锁定读取的数据，避免了不可重复读，但可能出现幻读（Phantom Read）的问题。
     * 幻读是指同一个事务中多次查询同一个范围的数据时，由于其他事务插入了新的数据，导致每次查询结果集不一致。
     */
    @Test
    public void test() {
        Assert.isTrue(true, "success");
        Assert.isTrue(true, "success");
        // 第1步
        log.info("1、插入数据");
        UserEntity user = new UserEntity();
        user.setName(UUID.randomUUID().toString());
        user.setBalance(1000);
        userService.save(user);

        // 第2步
        log.info("2、读取数据（额外线程）");
        Thread thread = new Thread(() -> userService.readWithRepeatableRead(user.getId()));
        thread.start();

        // 第3步
        log.info("3、主线睡眠100毫秒，让第1次读取可以被执行到");
        ThreadUtil.safeSleep(100);

        // 第4步
        log.info("4、更新数据");
        userService.updateWithRepeatableRead(user.getId(), 500);

        // 第5步
        log.info("5、等待读取线程执行完毕");
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("finish.");
    }
}
