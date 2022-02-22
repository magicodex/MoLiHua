package jasmine.autoconfigure.security.springsecurity;

import jasmine.security.authorization.AccessDecisionManagerProvider;
import jasmine.security.authorization.FilterSecurityInterceptorPostProcessor;
import jasmine.security.subject.UserSubjectDetailsServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * <p>
 * 配置 Spring Security。
 * </p>
 *
 * @author mh.z
 */
@EnableWebSecurity
@Configuration
public class SpringSecurityAutoConfiguration extends WebSecurityConfigurerAdapter {
    private AccessDecisionManagerProvider accessDecisionManagerProvider;
    private UserSubjectDetailsServiceProvider userDetailsServiceProvider;

    public SpringSecurityAutoConfiguration(AccessDecisionManagerProvider accessDecisionManagerProvider,
                                           @Autowired(required = false) UserSubjectDetailsServiceProvider userDetailsServiceProvider) {
        this.accessDecisionManagerProvider = accessDecisionManagerProvider;
        this.userDetailsServiceProvider = userDetailsServiceProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 允许所有人访问静态目录 /static/
                .antMatchers("/static/**")
                .permitAll()
                // 没有特别说明的其它请求由访问决策管理器决定能否访问
                .anyRequest()
                .denyAll()
                // 设置访问决策管理器
                .withObjectPostProcessor(new FilterSecurityInterceptorPostProcessor(accessDecisionManager()));

        // 自定义登录页面和认证失败后跳转的页面
        http.formLogin()
                .loginPage("/login")
                .failureForwardUrl("/login")
                .permitAll();

        // 自定义登出后跳转的页面
        http.logout()
                .logoutSuccessUrl("/login")
                .permitAll();

        // 不启用 CSRF 特殊处理
        http.csrf()
                .disable();

        // 同域的可以被嵌套
        http.headers()
                .frameOptions()
                .sameOrigin();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() {
        UserDetailsService userDetailsService = null;

        if (userDetailsServiceProvider != null) {
            userDetailsService = userDetailsServiceProvider.getService();
        } else {
            userDetailsService = new InMemoryUserDetailsManager();
        }

        return userDetailsService;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return accessDecisionManagerProvider.getManager();
    }

}
