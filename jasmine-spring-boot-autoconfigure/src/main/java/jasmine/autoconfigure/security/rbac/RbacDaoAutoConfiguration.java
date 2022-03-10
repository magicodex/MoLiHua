package jasmine.autoconfigure.security.rbac;

import jasmine.security.config.JasmineSecurityConfig;
import jasmine.security.rbac.mapper.SecFunctionMapper;
import jasmine.security.rbac.mapper.SecResourceMapper;
import jasmine.security.rbac.mapper.SecRoleMapper;
import jasmine.security.rbac.dao.SecFunctionPermissionRelationDao;
import jasmine.security.rbac.dao.SecFunctionResourceRelationDao;
import jasmine.security.rbac.dao.SecFunctionDao;
import jasmine.security.rbac.dao.SecMenuFunctionRelationDao;
import jasmine.security.rbac.dao.SecMenuDao;
import jasmine.security.rbac.dao.SecMenuTemplateDao;
import jasmine.security.rbac.dao.SecPermissionResourceRelationDao;
import jasmine.security.rbac.dao.SecPermissionDao;
import jasmine.security.rbac.dao.SecResourceDao;
import jasmine.security.rbac.dao.SecRoleFunctionRelationDao;
import jasmine.security.rbac.dao.SecRoleDao;
import jasmine.security.rbac.dao.SecUserRoleRelationDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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

    @Bean
    public SecRoleDao secRoleDao(SecRoleMapper baseMapper) {
        return new SecRoleDao(baseMapper);
    }

    @Bean
    public SecFunctionDao secFunctionDao(SecFunctionMapper baseMapper) {
        return new SecFunctionDao(baseMapper);
    }

    @Bean
    public SecPermissionDao secPermissionDao() {
        return new SecPermissionDao();
    }

    @Bean
    public SecResourceDao secResourceDao(SecResourceMapper baseMapper) {
        return new SecResourceDao(baseMapper);
    }

    @Bean
    public SecUserRoleRelationDao secUserRoleRelationDao() {
        return new SecUserRoleRelationDao();
    }

    @Bean
    public SecRoleFunctionRelationDao secRoleFunctionRelationDao() {
        return new SecRoleFunctionRelationDao();
    }

    @Bean
    public SecFunctionResourceRelationDao secFunctionResourceRelationDao() {
        return new SecFunctionResourceRelationDao();
    }

    @Bean
    public SecFunctionPermissionRelationDao secFunctionPermissionRelationDao() {
        return new SecFunctionPermissionRelationDao();
    }

    @Bean
    public SecPermissionResourceRelationDao secPermissionResourceRelationDao() {
        return new SecPermissionResourceRelationDao();
    }

    //
    // 菜单
    //

    @Bean
    public SecMenuDao secMenuDao() {
        return new SecMenuDao();
    }

    @Bean
    public SecMenuTemplateDao secMenuTemplateDao() {
        return new SecMenuTemplateDao();
    }

    @Bean
    public SecMenuFunctionRelationDao secMenuFunctionRelationDao() {
        return new SecMenuFunctionRelationDao();
    }

}
