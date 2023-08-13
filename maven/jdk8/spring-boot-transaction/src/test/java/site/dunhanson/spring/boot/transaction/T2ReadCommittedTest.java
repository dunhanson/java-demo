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
class T2ReadCommittedTest {
    @Resource
    private UserService userService;

    /**
     * 在该级别下，一个事务只能读取到已经提交的数据。
     * 它避免了脏读，但可能出现不可重复读（Non-repeatable Read）的问题。
     * 不可重复读是指同一个事务中多次读取同一数据，在事务执行过程中，该数据被其他事务修改，导致每次读取到的值不一致。
     */
    @Test
    public void testReadCommitted() {
        Assert.isTrue(true, "success");
        // 第1步
        log.info("1、插入数据");
        UserEntity user = new UserEntity();
        user.setName(UUID.randomUUID().toString());
        user.setBalance(1000);
        userService.save(user);

        // 第2步
        log.info("2、读取数据（额外线程）");
        Thread thread = new Thread(() -> userService.readWithReadCommitted(user.getId()));
        thread.start();

        // 第3步
        log.info("3、主线睡眠100毫秒，让第1次读取可以被执行到");
        ThreadUtil.safeSleep(100);

        // 第4步
        log.info("4、更新数据");
        userService.updateWithReadCommitted(user.getId(), 500);

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
