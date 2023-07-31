package site.dunhanson.spring.boot.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import site.dunhanson.spring.boot.transaction.dao.UserMapper;
import site.dunhanson.spring.boot.transaction.model.entity.LogEntity;
import site.dunhanson.spring.boot.transaction.model.entity.UserEntity;
import site.dunhanson.spring.boot.transaction.service.intf.LogService;
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
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Resource
    private LogService logService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int add(UserEntity user) {
        int rows = this.baseMapper.insert(user);
        LogEntity log = new LogEntity();
        log.setMessage("添加日志信息");
        logService.add(log);
        return rows;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int add1(UserEntity user) {
        int rows = this.baseMapper.insert(user);
        LogEntity log = new LogEntity();
        log.setMessage("添加日志信息");
        logService.add1(log);
        return rows;
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public int add2(UserEntity user) {
        int rows = this.baseMapper.insert(user);
        LogEntity log = new LogEntity();
        log.setMessage("添加日志信息");
        logService.add2(log);
        return rows;
    }
}
