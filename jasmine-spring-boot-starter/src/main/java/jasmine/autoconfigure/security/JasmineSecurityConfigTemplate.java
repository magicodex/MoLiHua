package jasmine.autoconfigure.security;

import jasmine.security.subject.UserSubjectDetailsService;
import org.springframework.security.oauth2.provider.ClientDetailsService;

/**
 * @author mh.z
 */
public interface JasmineSecurityConfigTemplate {

    /**
     * 注册 UserSubjectDetailsService 对象
     *
     * @return
     */
    UserSubjectDetailsService userSubjectDetailsService();

    /**
     * 注册 ClientDetailsService 对象
     *
     * @return
     */
    ClientDetailsService clientDetailsService();
}
