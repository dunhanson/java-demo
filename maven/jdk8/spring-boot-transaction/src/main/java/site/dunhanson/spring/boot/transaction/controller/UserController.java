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
    // 数据源事务管理器
    @Resource
    private DataSourceTransactionManager dataSourceTransactionManager;
    // 事务定义
    @Resource
    private TransactionDefinition transactionDefinition;


}

