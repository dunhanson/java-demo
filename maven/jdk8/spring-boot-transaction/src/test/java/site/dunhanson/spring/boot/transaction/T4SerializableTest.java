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
public class T4SerializableTest {
    @Resource
    private UserService userService;

    /**
     * 最高的隔离级别，它要求事务串行执行，完全避免了并发问题。
     * 在该级别下，事务之间互相看不到对方的操作，可以避免脏读、不可重复读和幻读等问题。
     * 然而，由于串行化执行，会牺牲一定的并发性能。
     */
    @Test
    public void test() {
        Assert.isTrue(true, "success");
        // 第1步
        log.info("1、插入数据");
        UserEntity user = new UserEntity();
        user.setName(UUID.randomUUID().toString());
        user.setBalance(1000);
        userService.save(user);

        // 第2步
        log.info("2、读取数据（额外线程）");
        Thread thread = new Thread(() -> userService.readWithSerializable(user.getId()));
        thread.start();

        // 第3步
        log.info("3、主线睡眠100毫秒，让第1次读取可以被执行到");
        ThreadUtil.safeSleep(100);

        // 第4步
        log.info("4、删除数据");
        userService.deleteWithSerializable(user.getId());

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
