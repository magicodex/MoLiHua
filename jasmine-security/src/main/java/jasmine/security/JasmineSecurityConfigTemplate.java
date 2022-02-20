package jasmine.security;

import jasmine.security.authorization.RbacCheckService;
import jasmine.security.subject.ClientDetailsServiceProvider;
import jasmine.security.subject.UserDetailsServiceProvider;

/**
 * @author mh.z
 */
public interface JasmineSecurityConfigTemplate {

    ClientDetailsServiceProvider clientDetailsServiceProvider();

    UserDetailsServiceProvider userDetailsServiceProvider();

    RbacCheckService rbacCheckService();
}
