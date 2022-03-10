package jasmine.demo.config;

import jasmine.framework.persistence.mybatisplus.tenant.IgnoreTableStrategy;
import jasmine.framework.persistence.mybatisplus.tenant.TenantConfigProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class DemoTenantConfig implements TenantConfigProcessor {

    @Override
    public void ignoreTable(IgnoreTableStrategy strategy) {

    }

}
