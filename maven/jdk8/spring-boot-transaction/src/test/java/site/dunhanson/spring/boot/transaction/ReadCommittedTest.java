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
class ReadCommittedTest {
    @Resource
    private UserService userService;

    @Test
    public void test() throws InterruptedException {
        Assert.isTrue(true, "success");
        // 1、创建一个用户，初始余额为1000
        UserEntity user = UserEntity.builder().name(UUID.randomUUID().toString()).balance(1000).build();
        userService.save(user);
        Integer userId = user.getId();

        // 2、使用新线程来执行更新用户余额的操作（模拟另一个事务）
        Thread thread = new Thread(() -> {
            // 更新用户余额为500
            userService.updateBalance(userId, 500);
        });
        thread.start();

        // 3、睡眠100毫秒
        ThreadUtil.safeSleep(100);

        // 4、在当前事务中读取用户余额，隔离级别为READ_COMMITTED
        System.out.println("getBalanceById start");
        int balance = userService.getBalanceByIdReadCommitted(userId);
        System.out.println("getBalanceById finish");
        System.out.println("User balance: " + balance);

        // 5、等待线程执行完毕
        thread.join();
    }

    /**
     * 不可重复读
     * <p>不可重复读是指同一个事务中多次读取同一数据，在事务执行过程中，该数据被其他事务修改，导致每次读取到的值不一致。</p>
     */
    @Test
    public void testNonRepeatableRead() throws InterruptedException {
        Assert.isTrue(true, "success");
        // 1、创建一个用户，初始余额为1000
        UserEntity user = UserEntity.builder().name(UUID.randomUUID().toString()).balance(1000).build();
        userService.save(user);
        Integer userId = user.getId();

        // 2、开辟新线程进行，读取两次记录
        Thread thread = new Thread(() -> userService.readTwiceInSameTransaction(userId));
        thread.start();

        // 3、睡眠100毫秒，让第1次读取可以成功执行到
        ThreadUtil.safeSleep(100);

        // 4、更新余额
        userService.updateBalanceSimple(userId, 500);

        // 5、等待线程执行完毕
        thread.join();
    }

}
