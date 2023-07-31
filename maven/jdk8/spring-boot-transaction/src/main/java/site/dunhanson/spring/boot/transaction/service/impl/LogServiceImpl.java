package site.dunhanson.spring.boot.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import site.dunhanson.spring.boot.transaction.dao.LogMapper;
import site.dunhanson.spring.boot.transaction.model.entity.LogEntity;
import site.dunhanson.spring.boot.transaction.service.intf.LogService;

/**
 * <p>
 * 日志 服务实现类
 * </p>
 *
 * @author dunhanson
 * @since 2023-07-31
 */
@Service
@Slf4j
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService {

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int add(LogEntity entity) {
        int result = this.baseMapper.insert(entity);
        log.info("添加日志的结果:{}", result);
        // 回滚事务，模仿发生异常
        // 这里为什么不写一个异常呢？因为异常会传递到外面的方法。
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int add1(LogEntity entity) {
        int result = this.baseMapper.insert(entity);
        log.info("添加日志的结果:{}", result);
        // 回滚事务，模仿发生异常
        // 这里为什么不写一个异常呢？因为异常会传递到外面的方法。
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return result;
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public int add2(LogEntity entity) {
        int result = this.baseMapper.insert(entity);
        log.info("添加日志的结果:{}", result);
        // 回滚事务，模仿发生异常
        // 这里为什么不写一个异常呢？因为异常会传递到外面的方法。
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return result;
    }
}
