package jasmine.autoconfigure.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mh.z
 */
@ConfigurationProperties(prefix = "jasmine.security")
public class JasmineSecurityProperties {
    /** 授权相关配置 */
    private Authorization authorization = new Authorization();

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    /**
     * 授权相关配置
     */
    public static class Authorization {
        /** 授权策略 */
        private String strategy = "default";
    }

}
