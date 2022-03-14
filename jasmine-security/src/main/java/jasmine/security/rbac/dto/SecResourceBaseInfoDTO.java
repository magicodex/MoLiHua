package jasmine.security.rbac.dto;

/**
 * @author mh.z
 */
public class SecResourceBaseInfoDTO {

    /** 资源ID */
    private Long resourceId;

    /** 资源类型 */
    private String resourceType;

    /** 访问策略 */
    private String accessPolicy;

    /** 访问方式 */
    private String accessMethod;

    /** 资源路径 */
    private String resourcePath;

    /** 冻结标志 */
    private Boolean frozenFlag;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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

}
