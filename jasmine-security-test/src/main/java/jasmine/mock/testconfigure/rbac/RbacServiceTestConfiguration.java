package jasmine.mock.testconfigure.rbac;

import jasmine.security.rbac.mapper.SecFunctionMapper;
import jasmine.security.rbac.mapper.SecResourceMapper;
import jasmine.security.rbac.mapper.SecRoleMapper;
import jasmine.security.rbac.service.SecFunctionPermissionRelationService;
import jasmine.security.rbac.service.SecFunctionResourceRelationService;
import jasmine.security.rbac.service.SecFunctionService;
import jasmine.security.rbac.service.SecMenuFunctionRelationService;
import jasmine.security.rbac.service.SecMenuService;
import jasmine.security.rbac.service.SecMenuTemplateService;
import jasmine.security.rbac.service.SecPermissionResourceRelationService;
import jasmine.security.rbac.service.SecPermissionService;
import jasmine.security.rbac.service.SecResourceService;
import jasmine.security.rbac.service.SecRoleFunctionRelationService;
import jasmine.security.rbac.service.SecRoleService;
import jasmine.security.rbac.service.SecUserRoleRelationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class RbacServiceTestConfiguration {

    @Bean
    public SecFunctionPermissionRelationService secFunctionPermissionRelationService() {
        return new SecFunctionPermissionRelationService();
    }

    @Bean
    public SecFunctionResourceRelationService secFunctionResourceRelationService() {
        return new SecFunctionResourceRelationService();
    }

    @Bean
    public SecFunctionService secFunctionService(SecFunctionMapper baseMapper) {
        return new SecFunctionService(baseMapper);
    }

    @Bean
    public SecMenuFunctionRelationService secMenuFunctionRelationService() {
        return new SecMenuFunctionRelationService();
    }

    @Bean
    public SecMenuService secMenuService() {
        return new SecMenuService();
    }

    @Bean
    public SecMenuTemplateService secMenuTemplateService() {
        return new SecMenuTemplateService();
    }

    @Bean
    public SecPermissionResourceRelationService secPermissionResourceRelationService() {
        return new SecPermissionResourceRelationService();
    }

    @Bean
    public SecPermissionService secPermissionService() {
        return new SecPermissionService();
    }

    @Bean
    public SecResourceService secResourceService(SecResourceMapper baseMapper) {
        return new SecResourceService(baseMapper);
    }

    @Bean
    public SecRoleFunctionRelationService secRoleFunctionRelationService() {
        return new SecRoleFunctionRelationService();
    }

    @Bean
    public SecRoleService secRoleService(SecRoleMapper baseMapper) {
        return new SecRoleService(baseMapper);
    }

    @Bean
    public SecUserRoleRelationService secUserRoleRelationService() {
        return new SecUserRoleRelationService();
    }

}
