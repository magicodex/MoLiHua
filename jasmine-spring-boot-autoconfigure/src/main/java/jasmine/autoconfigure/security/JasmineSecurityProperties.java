package jasmine.autoconfigure.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mh.z
 */
@ConfigurationProperties(prefix = "jasmine.security")
public class JasmineSecurityProperties {
    /** 授权相关配置 */
    private Authorization authorization = new Authorization();
    /** 表单登录相关配置 */
    private FormLogin formLogin = new FormLogin();
    /** 注销相关配置 */
    private Logout logout = new Logout();

    /** 公开资源 */
    private String[] publicLocations = new String[]{"/static/**"};


    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    public FormLogin getFormLogin() {
        return formLogin;
    }

    public void setFormLogin(FormLogin formLogin) {
        this.formLogin = formLogin;
    }

    public Logout getLogout() {
        return logout;
    }

    public void setLogout(Logout logout) {
        this.logout = logout;
    }

    public String[] getPublicLocations() {
        return publicLocations;
    }

    public void setPublicLocations(String[] publicLocations) {
        this.publicLocations = publicLocations;
    }

    /**
     * 授权相关配置
     */
    public static class Authorization {
        /** 授权策略 */
        private String strategy = "default";

        public String getStrategy() {
            return strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }
    }

    /**
     * 表单登录相关配置
     */
    public static class FormLogin {
        /** 登录页面 */
        private String loginPage = "/login";
        /** 认证成功跳转URL */
        private String successForwardUrl = "/";
        /** 认证失败跳转URL */
        private String failureForwardUrl = "/login";

        public String getLoginPage() {
            return loginPage;
        }

        public void setLoginPage(String loginPage) {
            this.loginPage = loginPage;
        }

        public String getSuccessForwardUrl() {
            return successForwardUrl;
        }

        public void setSuccessForwardUrl(String successForwardUrl) {
            this.successForwardUrl = successForwardUrl;
        }

        public String getFailureForwardUrl() {
            return failureForwardUrl;
        }

        public void setFailureForwardUrl(String failureForwardUrl) {
            this.failureForwardUrl = failureForwardUrl;
        }
    }

    /**
     * 注销相关配置
     */
    public static class Logout {
        /** 注销成功跳转URL */
        private String logoutSuccessUrl = "/login";

        public String getLogoutSuccessUrl() {
            return logoutSuccessUrl;
        }

        public void setLogoutSuccessUrl(String logoutSuccessUrl) {
            this.logoutSuccessUrl = logoutSuccessUrl;
        }
    }

}
