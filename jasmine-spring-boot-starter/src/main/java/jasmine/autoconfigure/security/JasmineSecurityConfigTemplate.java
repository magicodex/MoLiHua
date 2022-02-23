package jasmine.autoconfigure.security;

import jasmine.security.subject.ClientDetailsServiceProvider;
import jasmine.security.subject.UserSubjectDetailsServiceProvider;

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
     * 注册 UserSubjectDetailsServiceProvider 对象
     *
     * @return
     */
    UserSubjectDetailsServiceProvider userSubjectDetailsServiceProvider();
}
