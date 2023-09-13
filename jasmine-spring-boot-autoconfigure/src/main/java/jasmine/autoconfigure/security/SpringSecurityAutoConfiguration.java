package jasmine.autoconfigure.security;

import jasmine.autoconfigure.security.template.SpringSecurityConfigsTemplate;
import jasmine.security.config.JasmineSecurityConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    private SpringSecurityConfigsTemplate configsTemplate;

    public SpringSecurityAutoConfiguration(SpringSecurityConfigsTemplate configsTemplate) {
        this.configsTemplate = configsTemplate;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        configsTemplate.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        configsTemplate.configure(auth);
    }

    @ConditionalOnMissingBean(AuthenticationManager.class)
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
