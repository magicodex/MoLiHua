package jasmine.autoconfigure.security;

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

        {
            JasmineSecurityProperties.FormLogin formLogin = securityProperties.getFormLogin();
            FormLoginConfigurer formLoginConfigurer = http.formLogin();
            // 自定义登录页面
            formLoginConfigurer.loginPage(formLogin.getLoginPage());

            if (authenticationSuccessHandler != null) {
                formLoginConfigurer.successHandler(authenticationSuccessHandler);
            } else {
                // 自定义认证成功后跳转的页面
                formLoginConfigurer.successForwardUrl(formLogin.getSuccessForwardUrl());
            }

            if (authenticationFailureHandler != null) {
                formLoginConfigurer.failureHandler(authenticationFailureHandler);
            } else {
                // 自定义认证失败后跳转的页面
                formLoginConfigurer.failureForwardUrl(formLogin.getFailureForwardUrl());
            }

            formLoginConfigurer.permitAll();
        }

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

    @ConditionalOnMissingBean(AuthenticationManager.class)
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
