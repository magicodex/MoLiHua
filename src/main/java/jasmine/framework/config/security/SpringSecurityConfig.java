package jasmine.framework.config.security;

import jasmine.framework.security.UserSubject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collections;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 允许所有人访问静态目录 /static/
                .antMatchers("/static/**")
                .permitAll()
                // 没有特别说明的其它请求必须认证才能访问
                .anyRequest()
                .authenticated();

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
        // TODO 这里硬编码个用户，实际开发时需要重写这个方法
        UserSubject user = new UserSubject(100001L, "MoLiHua",
                passwordEncoder().encode("123456"),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        return new InMemoryUserDetailsManager(user);
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

}
