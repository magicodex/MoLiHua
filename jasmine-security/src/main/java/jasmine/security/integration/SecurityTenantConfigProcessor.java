package jasmine.security.integration;

import jasmine.framework.database.mybatisplus.tenant.IgnoreTableStrategy;
import jasmine.framework.database.mybatisplus.tenant.TenantConfigProcessor;

/**
 * @author mh.z
 */
public class SecurityTenantConfigProcessor implements TenantConfigProcessor {

    @Override
    public void ignoreTable(IgnoreTableStrategy strategy) {
        strategy.addIgnoreTable("sec_function");
        strategy.addIgnoreTable("sec_permission");
        strategy.addIgnoreTable("sec_resource");

        strategy.addIgnoreTable("sec_function_permission_rel");
        strategy.addIgnoreTable("sec_function_resource_rel");
        strategy.addIgnoreTable("sec_permission_resource_rel");

        strategy.addIgnoreTable("sec_function_i18n");
        strategy.addIgnoreTable("sec_permission_i18n");
        strategy.addIgnoreTable("sec_resource_i18n");
    }

}
