package site.dunhanson.spring.boot.transaction.service.intf;

import com.baomidou.mybatisplus.extension.service.IService;
import site.dunhanson.spring.boot.transaction.model.entity.UserEntity;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author dunhanson
 * @since 2023-07-31
 */
public interface UserService extends IService<UserEntity> {

    UserEntity readWithReadUncommitted(Integer id);

    void updateBalanceWithReadUncommitted(Integer id, int newBalance);

    void readWithReadCommitted(Integer id);

    void updateWithReadCommitted(Integer id, int newBalance);
}
