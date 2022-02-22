package jasmine.autoconfigure.security.rbac;

import jasmine.security.rbac.mapper.SecurityFunctionMapper;
import jasmine.security.rbac.mapper.SecurityResourceMapper;
import jasmine.security.rbac.mapper.SecurityRoleMapper;
import jasmine.security.rbac.service.SecurityFunctionPermissionRelationService;
import jasmine.security.rbac.service.SecurityFunctionResourceRelationService;
import jasmine.security.rbac.service.SecurityFunctionService;
import jasmine.security.rbac.service.SecurityMenuFunctionRelationService;
import jasmine.security.rbac.service.SecurityMenuService;
import jasmine.security.rbac.service.SecurityMenuTemplateService;
import jasmine.security.rbac.service.SecurityPermissionResourceRelationService;
import jasmine.security.rbac.service.SecurityPermissionService;
import jasmine.security.rbac.service.SecurityResourceService;
import jasmine.security.rbac.service.SecurityRoleFunctionRelationService;
import jasmine.security.rbac.service.SecurityRoleService;
import jasmine.security.rbac.service.SecurityUserRoleRelationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class RbacAutoConfiguration {

    @Bean
    public SecurityFunctionPermissionRelationService securityFunctionPermissionRelationService() {
        return new SecurityFunctionPermissionRelationService();
    }

    @Bean
    public SecurityFunctionResourceRelationService securityFunctionResourceRelationService() {
        return new SecurityFunctionResourceRelationService();
    }

    @Bean
    public SecurityFunctionService SecurityFunctionService(SecurityFunctionMapper baseMapper) {
        return new SecurityFunctionService(baseMapper);
    }

    @Bean
    public SecurityMenuFunctionRelationService securityMenuFunctionRelationService() {
        return new SecurityMenuFunctionRelationService();
    }

    @Bean
    public SecurityMenuService securityMenuService() {
        return new SecurityMenuService();
    }

    @Bean
    public SecurityMenuTemplateService securityMenuTemplateService() {
        return new SecurityMenuTemplateService();
    }

    @Bean
    public SecurityPermissionResourceRelationService securityPermissionResourceRelationService() {
        return new SecurityPermissionResourceRelationService();
    }

    @Bean
    public SecurityPermissionService securityPermissionService() {
        return new SecurityPermissionService();
    }

    @Bean
    public SecurityResourceService securityResourceService(SecurityResourceMapper baseMapper) {
        return new SecurityResourceService(baseMapper);
    }

    @Bean
    public SecurityRoleFunctionRelationService securityRoleFunctionRelationService() {
        return new SecurityRoleFunctionRelationService();
    }

    @Bean
    public SecurityRoleService securityRoleService(SecurityRoleMapper baseMapper) {
        return new SecurityRoleService(baseMapper);
    }

    @Bean
    public SecurityUserRoleRelationService securityUserRoleRelationService() {
        return new SecurityUserRoleRelationService();
    }

}
