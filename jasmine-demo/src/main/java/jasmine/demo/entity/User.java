package jasmine.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.mybatis.entity.BaseEntity;

/**
 * @author mh.z
 */
@TableName("sys_user")
public class User extends BaseEntity {

    @TableField("user_name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("tenant_id")
    private Long tenantId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

}
