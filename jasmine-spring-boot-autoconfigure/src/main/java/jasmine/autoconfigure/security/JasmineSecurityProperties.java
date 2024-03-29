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
    /** "记住我"相关配置 */
    private RememberMe rememberMe = new RememberMe();

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

    public RememberMe getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(RememberMe rememberMe) {
        this.rememberMe = rememberMe;
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
        /** 登录接口 */
        private String loginProcessingUrl = "/login";
        /** 认证成功后转发的请求URL */
        private String successForwardUrl = "/";
        /** 认证失败后转发的请求URL */
        private String failureForwardUrl = "/login";
        /** 认证成功后重定向的URL */
        private String successUrl = null;
        /** 认证失败后重定向的URL */
        private String failureUrl = null;
        /** 用户名参数 */
        private String usernameParameter = "username";
        /** 密码参数 */
        private String passwordParameter = "password";

        public String getLoginPage() {
            return loginPage;
        }

        public void setLoginPage(String loginPage) {
            this.loginPage = loginPage;
        }

        public String getLoginProcessingUrl() {
            return loginProcessingUrl;
        }

        public void setLoginProcessingUrl(String loginProcessingUrl) {
            this.loginProcessingUrl = loginProcessingUrl;
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

        public String getSuccessUrl() {
            return successUrl;
        }

        public void setSuccessUrl(String successUrl) {
            this.successUrl = successUrl;
        }

        public String getFailureUrl() {
            return failureUrl;
        }

        public void setFailureUrl(String failureUrl) {
            this.failureUrl = failureUrl;
        }

        public String getUsernameParameter() {
            return usernameParameter;
        }

        public void setUsernameParameter(String usernameParameter) {
            this.usernameParameter = usernameParameter;
        }

        public String getPasswordParameter() {
            return passwordParameter;
        }

        public void setPasswordParameter(String passwordParameter) {
            this.passwordParameter = passwordParameter;
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

    /**
     * "记住我"相关配置
     */
    public static class RememberMe {
        /** 若不指定该值，服务重启后"记住我"可能失效 */
        private String key;
        /** 是否开启"记住我"功能 */
        private Boolean enabled = false;
        /** cookie 名称 */
        private String cookieName = "remember-me";
        /** 参数名称 */
        private String parameterName = "remember-me";

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }

        public String getCookieName() {
            return cookieName;
        }

        public void setCookieName(String cookieName) {
            this.cookieName = cookieName;
        }

        public String getParameterName() {
            return parameterName;
        }

        public void setParameterName(String parameterName) {
            this.parameterName = parameterName;
        }
    }

}
