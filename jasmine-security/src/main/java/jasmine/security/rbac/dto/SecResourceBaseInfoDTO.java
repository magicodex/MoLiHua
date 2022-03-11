package jasmine.security.rbac.dto;

/**
 * @author mh.z
 */
public class SecResourceBaseInfoDTO {
    /** 资源ID */
    private Long resourceId;
    /** 访问策略 */
    private String accessPolicy;
    /** 冻结标志 */
    private Boolean frozenFlag;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getAccessPolicy() {
        return accessPolicy;
    }

    public void setAccessPolicy(String accessPolicy) {
        this.accessPolicy = accessPolicy;
    }

    public Boolean getFrozenFlag() {
        return frozenFlag;
    }

    public void setFrozenFlag(Boolean frozenFlag) {
        this.frozenFlag = frozenFlag;
    }

}
