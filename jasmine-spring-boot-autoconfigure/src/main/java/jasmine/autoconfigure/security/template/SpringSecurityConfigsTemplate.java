package jasmine.autoconfigure.security.template;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author mh.z
 */
public interface SpringSecurityConfigsTemplate {

    /**
     * 修改配置
     *
     * @param http
     * @throws Exception
     */
    void configure(HttpSecurity http) throws Exception;

    /**
     * 修改配置
     *
     * @param auth
     * @throws Exception
     */
    void configure(AuthenticationManagerBuilder auth) throws Exception;
}
