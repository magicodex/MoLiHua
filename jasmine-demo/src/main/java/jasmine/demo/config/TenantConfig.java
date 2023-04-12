package jasmine.demo.config;

import jasmine.framework.database.mybatisplus.tenant.IgnoreTableStrategy;
import jasmine.framework.database.mybatisplus.tenant.TenantConfigProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class TenantConfig implements TenantConfigProcessor {

    @Override
    public void ignoreTable(IgnoreTableStrategy strategy) {
        //
    }

}
