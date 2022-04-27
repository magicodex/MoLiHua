package jasmine.autoconfigure.security;

import jasmine.security.authorization.FilterSecurityInterceptorPostProcessor;
import jasmine.security.config.JasmineSecurityConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

    public SpringSecurityAutoConfiguration(JasmineSecurityProperties securityProperties,
                                           AccessDecisionManager accessDecisionManager) {
        this.securityProperties = securityProperties;
        this.accessDecisionManager = accessDecisionManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 允许所有人访问静态目录 /static/
                .antMatchers(securityProperties.getStaticLocations())
                .permitAll()
                // 没有特别说明的其它请求由访问决策管理器决定能否访问
                .anyRequest()
                .denyAll()
                // 设置访问决策管理器
                .withObjectPostProcessor(new FilterSecurityInterceptorPostProcessor(accessDecisionManager));

        JasmineSecurityProperties.FormLogin formLogin = securityProperties.getFormLogin();
        // 自定义登录页面和认证失败后跳转的页面
        http.formLogin()
                .loginPage(formLogin.getLoginPage())
                .failureForwardUrl(formLogin.getFailureForwardUrl())
                .permitAll();

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

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
