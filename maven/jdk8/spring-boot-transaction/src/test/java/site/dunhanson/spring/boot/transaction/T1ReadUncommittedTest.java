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

/**
 * 读未提交
 * @author dunhanson
 * @since 2023-08-13
 * @version 1.0.0
 */
@Slf4j
@SpringBootTest
class T1ReadUncommittedTest {
    @Resource
    private UserService userService;

    /**
     * 1、插入数据
     * 2、第1次读取数据
     * 3、对数据进行更新
     * 4、第2次读取数据
     */
    @Test
    public void testReadUncommitted() {
        Assert.isTrue(true, "success");
        // 第1步
        log.info("1、插入数据");
        UserEntity user = new UserEntity();
        user.setName(UUID.randomUUID().toString());
        user.setBalance(1000);
        userService.save(user);

        // 第2步
        log.info("2、第1次读取数据");
        UserEntity userEntity1 = userService.readWithReadUncommitted(user.getId());
        log.info("2.1 userEntity1 id:{}, balance:{}", userEntity1.getId(), userEntity1.getBalance());

        // 第3步
        log.info("3、对数据进行更新");
        Thread thread = new Thread(() -> userService.updateBalanceWithReadUncommitted(user.getId(), 500));
        thread.start();

        // 第4步
        log.info("4、睡眠100毫秒，让更新操作可以执行到");
        ThreadUtil.safeSleep(100);

        // 第5步
        log.info("5、第2次读取数据");
        UserEntity userEntity12= userService.readWithReadUncommitted(user.getId());
        log.info("5.1 userEntity2 id:{}, balance:{}", userEntity12.getId(), userEntity12.getBalance());

        // 第6步
        log.info("6、等待更新线程执行完毕");
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("finish.");
    }

}
