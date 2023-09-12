package jasmine.autoconfigure.security;

import jasmine.autoconfigure.security.template.SpringSecurityConfigsTemplate;
import jasmine.framework.common.util.SpringUtil;
import jasmine.framework.common.util.StringUtil;
import jasmine.security.authentication.RememberMeSuccessHandler;
import jasmine.security.authorization.FilterSecurityInterceptorPostProcessor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author mh.z
 */
public class SpringSecurityConfiguration implements SpringSecurityConfigsTemplate, ApplicationContextAware {
    protected ApplicationContext applicationContext;
    protected JasmineSecurityProperties securityProperties;

    public SpringSecurityConfiguration(JasmineSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AccessDecisionManager accessDecisionManager = SpringUtil.getBean(applicationContext,
                AccessDecisionManager.class, true);
        AuthenticationEntryPoint authenticationEntryPoint = SpringUtil.getBean(applicationContext,
                AuthenticationEntryPoint.class, false);

        http.authorizeRequests()
                // 允许所有人访问公开资源
                .antMatchers(securityProperties.getPublicLocations())
                .permitAll()
                // 没有特别说明的其它请求由访问决策管理器决定能否访问
                .anyRequest()
                .denyAll()
                // 设置访问决策管理器
                .withObjectPostProcessor(new FilterSecurityInterceptorPostProcessor(accessDecisionManager));

        if (authenticationEntryPoint != null) {
            http.exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint);
        }

        // 配置表单登录
        configFormLogin(http);
        // 配置"记住我"功能
        configRememberMe(http);

        JasmineSecurityProperties.Logout logout = securityProperties.getLogout();
        JasmineSecurityProperties.RememberMe rememberMe = securityProperties.getRememberMe();
        String[] cookieNamesToClear = new String[]{rememberMe.getCookieName()};
        // 自定义登出后跳转的页面
        http.logout()
                .logoutSuccessUrl(logout.getLogoutSuccessUrl())
                .permitAll()
                .deleteCookies(cookieNamesToClear);

        // 不启用 CSRF 特殊处理
        http.csrf()
                .disable();

        // 同域的可以被嵌套
        http.headers()
                .frameOptions()
                .sameOrigin();
    }

    /**
     * 配置表单登录
     *
     * @param http
     * @throws Exception
     */
    public void configFormLogin(HttpSecurity http) throws Exception {
        JasmineSecurityProperties.FormLogin formLogin = securityProperties.getFormLogin();

        AuthenticationSuccessHandler authenticationSuccessHandler = SpringUtil.getBean(applicationContext,
                AuthenticationSuccessHandler.class, false);
        AuthenticationFailureHandler authenticationFailureHandler = SpringUtil.getBean(applicationContext,
                AuthenticationFailureHandler.class, false);

        FormLoginConfigurer formLoginConfigurer = http.formLogin();
        // 自定义登录页面和登录接口
        formLoginConfigurer.loginPage(formLogin.getLoginPage());
        formLoginConfigurer.loginProcessingUrl(formLogin.getLoginProcessingUrl());
        // 自定义用户名和密码参数
        formLoginConfigurer.usernameParameter(formLogin.getUsernameParameter());
        formLoginConfigurer.passwordParameter(formLogin.getPasswordParameter());

        if (authenticationSuccessHandler != null) {
            formLoginConfigurer.successHandler(authenticationSuccessHandler);
        } else if (StringUtil.isNotEmpty(formLogin.getSuccessUrl())) {
            // 认证成功后重定向的URL
            formLoginConfigurer.defaultSuccessUrl(formLogin.getSuccessUrl(), true);
        } else if (StringUtil.isNotEmpty(formLogin.getSuccessForwardUrl())) {
            // 认证成功后转发的请求URL
            formLoginConfigurer.successForwardUrl(formLogin.getSuccessForwardUrl());
        }

        if (authenticationFailureHandler != null) {
            formLoginConfigurer.failureHandler(authenticationFailureHandler);
        } else if (StringUtil.isNotEmpty(formLogin.getFailureUrl())) {
            // 认证失败后重定向的URL
            formLoginConfigurer.failureUrl(formLogin.getFailureUrl());
        } else if (StringUtil.isNotEmpty(formLogin.getFailureForwardUrl())) {
            // 认证失败后转发的请求URL
            formLoginConfigurer.failureForwardUrl(formLogin.getFailureForwardUrl());
        }

        formLoginConfigurer.permitAll();
    }

    /**
     * 配置"记住我"功能
     *
     * @param http
     * @throws Exception
     */
    public void configRememberMe(HttpSecurity http) throws Exception {
        JasmineSecurityProperties.RememberMe rememberMe = securityProperties.getRememberMe();

        UserDetailsService userDetailsService = SpringUtil.getBean(applicationContext,
                UserDetailsService.class, false);
        RememberMeServices rememberMeServices = SpringUtil.getBean(applicationContext,
                RememberMeServices.class, false);
        PersistentTokenRepository rememberMeTokenRepository = SpringUtil.getBean(applicationContext,
                PersistentTokenRepository.class, false);
        RememberMeSuccessHandler rememberMeSuccessHandler = SpringUtil.getBean(applicationContext,
                RememberMeSuccessHandler.class, false);

        if (Boolean.TRUE.equals(rememberMe.getEnabled())) {
            RememberMeConfigurer rememberMeConfigurer = http.rememberMe();

            String cookieName = rememberMe.getCookieName();
            rememberMeConfigurer.rememberMeCookieName(cookieName);

            String parameterName = rememberMe.getParameterName();
            rememberMeConfigurer.rememberMeParameter(parameterName);

            String key = rememberMe.getKey();
            if (StringUtil.isNotEmpty(key)) {
                rememberMeConfigurer.key(key);
            }

            if (userDetailsService != null) {
                rememberMeConfigurer.userDetailsService(userDetailsService);
            }

            if (rememberMeServices != null) {
                rememberMeConfigurer.rememberMeServices(rememberMeServices);
            }

            if (rememberMeTokenRepository != null) {
                rememberMeConfigurer.tokenRepository(rememberMeTokenRepository);
            }

            if (rememberMeSuccessHandler != null) {
                rememberMeConfigurer.authenticationSuccessHandler(rememberMeSuccessHandler);
            }
        }
    }

}
