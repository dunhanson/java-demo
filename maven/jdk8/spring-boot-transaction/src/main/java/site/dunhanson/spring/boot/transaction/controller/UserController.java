package site.dunhanson.spring.boot.transaction.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.dunhanson.spring.boot.transaction.model.entity.UserEntity;
import site.dunhanson.spring.boot.transaction.service.intf.UserService;
import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author dunhanson
 * @since 2023-07-31
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Resource
    private TransactionDefinition transactionDefinition;

    /**
     * 编程式事务
     */
    @RequestMapping("delete")
    public Boolean delete(Integer id) {
        // 1、开启事务
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
        // 2、删除数据
        boolean result = userService.removeById(id);
        // 3、提交事务 or 回滚食物
        // 回滚
        dataSourceTransactionManager.rollback(transaction);
        // 提交
        //dataSourceTransactionManager.commit(transaction);
        log.info("delete:{}", result);
        return result;
    }

    /**
     * 完整、规范的代码
     */
    @RequestMapping("delete1")
    public Boolean delete1(Integer id) {
        TransactionStatus transaction = null;
        boolean result = false;
        try {
            // 1、开启事务
            transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
            // 2、删除数据
            result = userService.removeById(id);
            log.info("delete:{}", result);
            // 3、提交事务
            dataSourceTransactionManager.commit(transaction);
        } catch (Exception e) {
            // 4、回滚
            if(Objects.nonNull(transaction)) {
                dataSourceTransactionManager.rollback(transaction);
            }
        }
        return result;
    }

    /**
     * 声明式事务（注解）
     */
    @Transactional
    @RequestMapping("delete2")
    public Boolean delete2(Integer id) {
        // 删除数据
        boolean result = userService.removeById(id);
        log.info("delete:{}", result);
        return result;
    }

    /**
     * 没有处理的异常会自动回滚事务
     */
    @Transactional
    @RequestMapping("delete3")
    public Boolean delete3(Integer id) {
        // 删除数据
        boolean result = userService.removeById(id);
        int x = 8 / 0 ;
        log.info("delete:{}", result);
        return result;
    }

    /**
     * 处理后的异常不会自动回滚事务
     */
    @Transactional
    @RequestMapping("delete4")
    public Boolean delete4(Integer id) {
        boolean result = false;
        try {
            result = userService.removeById(id);
            log.info("delete:{}", result);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return result;
    }

    @RequestMapping("add")
    @Transactional(propagation =  Propagation.REQUIRED)
    public int add(String username, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        return userService.add(user);
    }
}

