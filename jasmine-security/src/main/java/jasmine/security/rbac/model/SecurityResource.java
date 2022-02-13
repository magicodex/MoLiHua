package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 资源。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_resource")
public class SecurityResource {
    @TableId
    private Long id;

    /** 资源类型 */
    @TableField("resource_type")
    private String resourceType;

    /** 访问策略 */
    @TableField("access_policy")
    private String accessPolicy;

    /** 访问方式 */
    @TableField("access_method")
    private String accessMethod;

    /** 资源路径 */
    @TableField("resource_path")
    private String resourcePath;

    /** 冻结标志 */
    @TableField("frozen_flag")
    private Boolean frozenFlag;

    /** 备注 */
    @TableField("remark")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getAccessPolicy() {
        return accessPolicy;
    }

    public void setAccessPolicy(String accessPolicy) {
        this.accessPolicy = accessPolicy;
    }

    public String getAccessMethod() {
        return accessMethod;
    }

    public void setAccessMethod(String accessMethod) {
        this.accessMethod = accessMethod;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public Boolean getFrozenFlag() {
        return frozenFlag;
    }

    public void setFrozenFlag(Boolean frozenFlag) {
        this.frozenFlag = frozenFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
