package jasmine.autoconfigure.security.rbac;

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
public class RbacAutoConfiguration {

    @Bean
    public SecFunctionPermissionRelationService securityFunctionPermissionRelationService() {
        return new SecFunctionPermissionRelationService();
    }

    @Bean
    public SecFunctionResourceRelationService securityFunctionResourceRelationService() {
        return new SecFunctionResourceRelationService();
    }

    @Bean
    public SecFunctionService SecurityFunctionService(SecFunctionMapper baseMapper) {
        return new SecFunctionService(baseMapper);
    }

    @Bean
    public SecMenuFunctionRelationService securityMenuFunctionRelationService() {
        return new SecMenuFunctionRelationService();
    }

    @Bean
    public SecMenuService securityMenuService() {
        return new SecMenuService();
    }

    @Bean
    public SecMenuTemplateService securityMenuTemplateService() {
        return new SecMenuTemplateService();
    }

    @Bean
    public SecPermissionResourceRelationService securityPermissionResourceRelationService() {
        return new SecPermissionResourceRelationService();
    }

    @Bean
    public SecPermissionService securityPermissionService() {
        return new SecPermissionService();
    }

    @Bean
    public SecResourceService securityResourceService(SecResourceMapper baseMapper) {
        return new SecResourceService(baseMapper);
    }

    @Bean
    public SecRoleFunctionRelationService securityRoleFunctionRelationService() {
        return new SecRoleFunctionRelationService();
    }

    @Bean
    public SecRoleService securityRoleService(SecRoleMapper baseMapper) {
        return new SecRoleService(baseMapper);
    }

    @Bean
    public SecUserRoleRelationService securityUserRoleRelationService() {
        return new SecUserRoleRelationService();
    }

}
