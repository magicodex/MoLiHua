package jasmine.security.mock.testconfigure.rbac;

import jasmine.security.rbac.dao.SecFunctionDao;
import jasmine.security.rbac.dao.SecMenuDao;
import jasmine.security.rbac.dao.SecMenuTemplateDao;
import jasmine.security.rbac.dao.SecPermissionDao;
import jasmine.security.rbac.dao.SecResourceDao;
import jasmine.security.rbac.dao.SecRoleDao;
import jasmine.security.rbac.mapper.SecFunctionMapper;
import jasmine.security.rbac.mapper.SecResourceMapper;
import jasmine.security.rbac.mapper.SecRoleMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class RbacDaoTestConfiguration {

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

}
