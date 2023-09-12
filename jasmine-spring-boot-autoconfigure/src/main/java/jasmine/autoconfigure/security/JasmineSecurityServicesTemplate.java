package jasmine.autoconfigure.security;

import jasmine.security.subject.ClientSubjectDetailsService;
import jasmine.security.subject.UserSubjectDetailsService;

/**
 * @author mh.z
 */
public interface JasmineSecurityServicesTemplate {

    /**
     * 注册 UserSubjectDetailsService 对象
     *
     * @return
     */
    UserSubjectDetailsService userSubjectDetailsService();

    /**
     * 注册 ClientSubjectDetailsService 对象
     *
     * @return
     */
    ClientSubjectDetailsService clientSubjectDetailsService();
}
