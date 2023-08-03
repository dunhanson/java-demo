package site.dunhanson.spring.boot.transaction;

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

        // 让updateBalance执行
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3、在当前事务中读取用户余额，隔离级别为READ_COMMITTED
        System.out.println("getBalanceById start");
        int balance = userService.getBalanceByIdReadCommitted(userId);
        System.out.println("getBalanceById finish");
        System.out.println("User balance: " + balance);

        // 4、等待线程执行完毕
        thread.join();
    }

}
