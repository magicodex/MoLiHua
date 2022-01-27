package jasmine.security.config;

import jasmine.security.authorization.DynamicAccessDecisionManager;
import jasmine.security.subject.UserDetailsServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * <p>
 * 配置 Spring Security。
 * </p>
 *
 * @author mh.z
 */
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DynamicAccessDecisionManager accessDecisionManager;

    @Autowired
    private UserDetailsServiceProvider userDetailsServiceProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 允许所有人访问静态目录 /static/
                .antMatchers("/static/**")
                .permitAll()
                // 没有特别说明的其它请求必须认证才能访问
                .anyRequest()
                .authenticated()
                .withObjectPostProcessor(filterSecurityInterceptorPostProcessor());

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
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() {
        return userDetailsServiceProvider.getService();
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

    /**
     *
     * @return
     */
    private ObjectPostProcessor<FilterSecurityInterceptor> filterSecurityInterceptorPostProcessor() {
        return new ObjectPostProcessor<>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setAccessDecisionManager(accessDecisionManager);

                return object;
            }
        };
    }

}
