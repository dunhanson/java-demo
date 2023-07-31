package site.dunhanson.spring.boot.transaction.service.intf;

import com.baomidou.mybatisplus.extension.service.IService;
import site.dunhanson.spring.boot.transaction.model.entity.LogEntity;

/**
 * <p>
 * 日志 服务类
 * </p>
 *
 * @author dunhanson
 * @since 2023-07-31
 */
public interface LogService extends IService<LogEntity> {

    int add(LogEntity log);
}
