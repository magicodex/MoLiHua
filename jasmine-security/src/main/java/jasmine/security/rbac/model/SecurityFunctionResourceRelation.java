package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 关联功能和资源。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_function_resource_rel")
public class SecurityFunctionResourceRelation {
    @TableId
    private Long id;

    /** 功能ID */
    @TableField("function_id")
    private Long functionId;

    /** 资源ID */
    @TableField("resource_id")
    private Long resourceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

}
