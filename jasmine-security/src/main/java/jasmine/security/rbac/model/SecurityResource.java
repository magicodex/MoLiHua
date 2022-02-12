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

    /** 资源代码 */
    @TableField("resource_code")
    private String resourceCode;

    /** 资源名称 */
    @TableField("resource_name")
    private String resourceName;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
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

}
