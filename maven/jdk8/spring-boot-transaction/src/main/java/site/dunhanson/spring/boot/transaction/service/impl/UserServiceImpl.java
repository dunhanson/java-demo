package site.dunhanson.spring.boot.transaction.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import site.dunhanson.spring.boot.transaction.dao.UserMapper;
import site.dunhanson.spring.boot.transaction.model.entity.UserEntity;
import site.dunhanson.spring.boot.transaction.service.intf.UserService;
import javax.annotation.Resource;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author dunhanson
 * @since 2023-07-31
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Resource
    private DataSourceTransactionManager transactionManager;

    @Resource
    private TransactionDefinition transactionDefinition;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public UserEntity readWithReadUncommitted(Integer id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public void updateBalanceWithReadUncommitted(Integer id, int newBalance) {
        log.info("3.1、更新--->开启事务");
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        log.info("3.2、更新--->修改数据");
        UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setBalance(newBalance);
        this.baseMapper.updateById(entity);

        log.info("3.3、更新--->睡眠10s，好让第2次读取可以读取到脏数据");
        ThreadUtil.safeSleep(1000 * 10);

        log.info("3.4、更新--->提交事务");
        transactionManager.commit(transactionStatus);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void readWithReadCommitted(Integer id) {
        UserEntity userEntity1 = this.baseMapper.selectById(id);
        log.info("2.1、userEntity1 id:{} balance:{}", userEntity1.getId(), userEntity1.getBalance());

        log.info("2.2、睡眠1秒，好让更新操作可以可以被执行到。");
        ThreadUtil.safeSleep(1000);

        UserEntity userEntity2 = this.baseMapper.selectById(id);
        log.info("2.3、userEntity2 id:{} balance:{}", userEntity2.getId(), userEntity2.getBalance());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void updateWithReadCommitted(Integer id, int newBalance) {
        UserEntity updateEntity = new UserEntity();
        updateEntity.setId(id);
        updateEntity.setBalance(newBalance);
        this.baseMapper.updateById(updateEntity);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void readWithRepeatableRead(Integer id) {
        UserEntity userEntity1 = this.baseMapper.selectById(id);
        log.info("2.1、userEntity1 id:{} balance:{}", userEntity1.getId(), userEntity1.getBalance());

        log.info("2.2、睡眠1秒，好让更新操作可以可以被执行到。");
        ThreadUtil.safeSleep(1000);

        UserEntity userEntity2 = this.baseMapper.selectById(id);
        log.info("2.3、userEntity2 id:{} balance:{}", userEntity2.getId(), userEntity2.getBalance());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public void updateWithRepeatableRead(Integer id, int newBalance) {
        UserEntity updateEntity = new UserEntity();
        updateEntity.setId(id);
        updateEntity.setBalance(newBalance);
        this.baseMapper.updateById(updateEntity);
    }
}
