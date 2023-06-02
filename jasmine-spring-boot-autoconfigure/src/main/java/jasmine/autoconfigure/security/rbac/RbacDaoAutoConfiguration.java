package jasmine.autoconfigure.security.rbac;

import jasmine.security.config.JasmineSecurityConfig;
import jasmine.security.rbac.dao.SecFunctionDAO;
import jasmine.security.rbac.dao.SecMenuDAO;
import jasmine.security.rbac.dao.SecMenuTemplateDAO;
import jasmine.security.rbac.dao.SecPermissionDAO;
import jasmine.security.rbac.dao.SecResourceDAO;
import jasmine.security.rbac.dao.SecRoleDAO;
import jasmine.security.rbac.mapper.SecFunctionMapper;
import jasmine.security.rbac.mapper.SecResourceMapper;
import jasmine.security.rbac.mapper.SecRoleMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@ConditionalOnClass(JasmineSecurityConfig.class)
@MapperScan("jasmine.security.**.mapper")
@Configuration
public class RbacDaoAutoConfiguration {

    //
    // 权限
    //

    @ConditionalOnMissingBean(SecRoleDAO.class)
    @Bean
    public SecRoleDAO secRoleDAO(SecRoleMapper baseMapper) {
        return new SecRoleDAO(baseMapper);
    }

    @ConditionalOnMissingBean(SecFunctionDAO.class)
    @Bean
    public SecFunctionDAO secFunctionDAO(SecFunctionMapper baseMapper) {
        return new SecFunctionDAO(baseMapper);
    }

    @ConditionalOnMissingBean(SecPermissionDAO.class)
    @Bean
    public SecPermissionDAO secPermissionDAO() {
        return new SecPermissionDAO();
    }

    @ConditionalOnMissingBean(SecResourceDAO.class)
    @Bean
    public SecResourceDAO secResourceDAO(SecResourceMapper baseMapper) {
        return new SecResourceDAO(baseMapper);
    }

    //
    // 菜单
    //

    @ConditionalOnMissingBean(SecMenuDAO.class)
    @Bean
    public SecMenuDAO secMenuDAO() {
        return new SecMenuDAO();
    }

    @ConditionalOnMissingBean(SecMenuTemplateDAO.class)
    @Bean
    public SecMenuTemplateDAO secMenuTemplateDAO() {
        return new SecMenuTemplateDAO();
    }

}
