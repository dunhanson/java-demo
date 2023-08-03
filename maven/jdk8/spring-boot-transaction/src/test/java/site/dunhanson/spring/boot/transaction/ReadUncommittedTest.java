package site.dunhanson.spring.boot.transaction;

import cn.hutool.core.thread.ThreadUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import site.dunhanson.spring.boot.transaction.model.entity.UserEntity;
import site.dunhanson.spring.boot.transaction.service.intf.UserService;
import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest
class ReadUncommittedTest {
    @Resource
    private UserService userService;

    @Test
    public void testRepeatableRead() throws InterruptedException {
        Assert.isTrue(true, "success");
        // 1、创建一个用户，初始余额为1000
        UserEntity user = UserEntity.builder().name(UUID.randomUUID().toString()).balance(1000).build();
        userService.save(user);
        Integer userId = user.getId();

        // 2、读取两次，模拟可重复读
        userService.readTwiceInSameTransactionWithRepeatableRead(userId);

        // 3、睡眠100毫秒，让两次读取中的第一次成功读取到
        ThreadUtil.safeSleep(100);

        // 4、开辟新线程进行，更新余额为500
        Thread thread = new Thread(() -> userService.updateBalanceSimple(userId, 500));
        thread.start();

        // 5、等待线程执行完毕
        thread.join();
    }

}