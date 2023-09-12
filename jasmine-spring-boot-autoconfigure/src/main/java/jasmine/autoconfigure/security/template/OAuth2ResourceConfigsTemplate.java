package jasmine.autoconfigure.security.template;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * @author mh.z
 */
public interface OAuth2ResourceConfigsTemplate {

    /**
     * 修改配置
     *
     * @param http
     * @throws Exception
     */
    void configure(HttpSecurity http) throws Exception;
}
