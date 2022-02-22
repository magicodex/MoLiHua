package jasmine.security.rbac.dto;

/**
 * @author mh.z
 */
public class SecResourceBaseInfoDTO {
    /** 资源ID */
    private Long resourceId;
    /** 访问方式 */
    private String accessMethod;
    /** 资源路径 */
    private String resourcePath;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
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

}
