package jasmine.security;

import jasmine.security.authorization.RbacCheckService;
import jasmine.security.subject.ClientDetailsServiceProvider;
import jasmine.security.subject.UserDetailsServiceProvider;

/**
 * <p>
 * 引入 jasmine-security 依赖后需注册以下 bean 才能正常运行，比如
 * <pre>{@code
 * class JasmineFrameworkConfig implements JasmineSecurityConfigTemplate {
 *
 *   @Bean
 *   @Override
 *   public ClientDetailsServiceProvider clientDetailsServiceProvider() {
 *       return ...;
 *   }
 *
 *   ...
 * }
 * }</pre>
 *
 * @author mh.z
 */
public interface JasmineSecurityConfigTemplate {

    /**
     * 注册 ClientDetailsServiceProvider 对象
     *
     * @return
     */
    ClientDetailsServiceProvider clientDetailsServiceProvider();

    /**
     * 注册 UserDetailsServiceProvider 对象
     *
     * @return
     */
    UserDetailsServiceProvider userDetailsServiceProvider();

    /**
     * 注册 RbacCheckService 对象
     *
     * @return
     */
    RbacCheckService rbacCheckService();
}
