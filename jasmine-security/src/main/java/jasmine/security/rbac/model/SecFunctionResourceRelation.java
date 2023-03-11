package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.mybatis.entity.BaseEntity;

/**
 * <p>
 * 关联功能和资源。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_function_resource_rel")
public class SecFunctionResourceRelation extends BaseEntity {

    /** 功能ID */
    @TableField("function_id")
    private Long functionId;

    /** 资源ID */
    @TableField("resource_id")
    private Long resourceId;

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
