package site.dunhanson.spring.boot.transaction.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 日志
 * </p>
 *
 * @author dunhanson
 * @since 2023-07-31
 */
@Getter
@Setter
  @TableName("log")
public class LogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * ID
     */
      private Integer id;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;

      /**
     * 消息
     */
      private String message;


}
