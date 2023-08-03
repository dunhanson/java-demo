package site.dunhanson.spring.boot.transaction.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import site.dunhanson.spring.boot.transaction.dao.UserMapper;
import site.dunhanson.spring.boot.transaction.model.entity.UserEntity;
import site.dunhanson.spring.boot.transaction.service.intf.UserService;

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

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public int getBalanceByIdReadUncommitted(Integer id) {
        UserEntity entity = this.baseMapper.selectById(id);
        if(entity == null) {
            return 0;
        }
        return entity.getBalance();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public int getBalanceByIdReadCommitted(Integer id) {
        UserEntity entity = this.baseMapper.selectById(id);
        if(entity == null) {
            return 0;
        }
        return entity.getBalance();
    }

    @Override
    public void updateBalance(Integer id, int newBalance) {
        System.out.println("updateBalance start");
        UserEntity entity = this.getById(id);
        if(entity == null) {
            return;
        }
        entity.setBalance(newBalance);
        this.baseMapper.updateById(entity);
        System.out.println("updateBalance finish");
        // 睡眠500毫秒，模拟事务执行的时间
        System.out.println("updateBalance sleep start");
        ThreadUtil.safeSleep(500);
        System.out.println("updateBalance sleep finish");
    }

    @Override
    public void updateBalanceSimple(Integer id, int newBalance) {
        UserEntity entity = this.getById(id);
        if(entity == null) {
            return;
        }
        entity.setBalance(newBalance);
        this.baseMapper.updateById(entity);
    }

    @Override
    public void readTwiceInSameTransaction(Integer id) {
        // 1、第1次获取
        UserEntity entity1 = this.getById(id);
        System.out.println("entity1 getBalance: " + entity1.getBalance());

        // 2、睡眠500毫秒，让外面的更新操作可以执行到
        ThreadUtil.safeSleep(500);
        // 3、第2次获取
        UserEntity entity2 = this.getById(id);
        System.out.println("entity2 getBalance: " + entity2.getBalance());
    }
}
