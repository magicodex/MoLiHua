package jasmine.security.rbac.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jasmine.framework.database.mybatisplus.annotation.I18n;
import jasmine.framework.database.mybatisplus.entity.BaseI18nEntity;

/**
 * <p>
 * 资源。
 * </p>
 *
 * @author mh.z
 */
@TableName("sec_resource")
public class SecResource extends BaseI18nEntity {

    /** 资源类别 */
    @TableField("resource_category")
    private String resourceCategory;

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
    @I18n
    @TableField(value = "remark", updateStrategy = FieldStrategy.IGNORED)
    private String remark;

    public String getResourceCategory() {
        return resourceCategory;
    }

    public void setResourceCategory(String resourceCategory) {
        this.resourceCategory = resourceCategory;
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
