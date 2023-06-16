package jasmine.autoconfigure.security;

import jasmine.framework.common.util.StringUtil;
import jasmine.security.authorization.FilterSecurityInterceptorPostProcessor;
import jasmine.security.config.JasmineSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * <p>
 * 配置 Spring Security。
 * </p>
 *
 * @author mh.z
 */
@EnableConfigurationProperties(JasmineSecurityProperties.class)
@ConditionalOnClass(JasmineSecurityConfig.class)
@EnableWebSecurity
@Configuration
public class SpringSecurityAutoConfiguration extends WebSecurityConfigurerAdapter {
    private JasmineSecurityProperties securityProperties;
    private AccessDecisionManager accessDecisionManager;
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;

    public SpringSecurityAutoConfiguration(JasmineSecurityProperties securityProperties,
                                           AccessDecisionManager accessDecisionManager,
                                           @Autowired(required = false) AuthenticationSuccessHandler authenticationSuccessHandler,
                                           @Autowired(required = false) AuthenticationFailureHandler authenticationFailureHandler) {
        this.securityProperties = securityProperties;
        this.accessDecisionManager = accessDecisionManager;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 允许所有人访问公开资源
                .antMatchers(securityProperties.getPublicLocations())
                .permitAll()
                // 没有特别说明的其它请求由访问决策管理器决定能否访问
                .anyRequest()
                .denyAll()
                // 设置访问决策管理器
                .withObjectPostProcessor(new FilterSecurityInterceptorPostProcessor(accessDecisionManager));

        // 配置表单登录
        configFormLogin(http);

        JasmineSecurityProperties.Logout logout = securityProperties.getLogout();
        // 自定义登出后跳转的页面
        http.logout()
                .logoutSuccessUrl(logout.getLogoutSuccessUrl())
                .permitAll();

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
    protected void configFormLogin(HttpSecurity http) throws Exception {
        JasmineSecurityProperties.FormLogin formLogin = securityProperties.getFormLogin();
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

    @ConditionalOnMissingBean(AuthenticationManager.class)
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
