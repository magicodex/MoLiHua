package jasmine.autoconfigure.framework.database;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mh.z
 */
@ConfigurationProperties(prefix = "jasmine.data")
public class MybatisPlusProperties {
    /** 加密相关配置 */
    private Crypto crypto = new Crypto();
    /** 租户相关配置 */
    private Tenant tenant = new Tenant();

    public Crypto getCrypto() {
        return crypto;
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    /**
     * 租户相关配置
     */
    public static class Tenant {
        /** 是否启用租户拦截器 */
        private Boolean enabled = false;

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }

    /**
     * 加密相关配置
     */
    public static class Crypto {
        /** 加密密码 */
        private String password = "";
        /** 加密盐值 */
        private String salt = "";

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }
    }

}
