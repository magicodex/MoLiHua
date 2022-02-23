package jasmine.demo.authentication.persistence.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mh.z
 */
@NoArgsConstructor
@Data
@TableName("sys_user")
public class UserEO extends BaseEntity {

    @TableField("user_name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("tenant_id")
    private Long tenantId;
}
