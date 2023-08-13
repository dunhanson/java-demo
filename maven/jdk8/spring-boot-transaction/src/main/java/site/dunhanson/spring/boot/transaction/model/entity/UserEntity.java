package site.dunhanson.spring.boot.transaction.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author dunhanson
 * @since 2023-07-31
 */
@Getter
@Setter
@TableName("user")
@NoArgsConstructor
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 账户余额
     */
    private Integer balance;
}
