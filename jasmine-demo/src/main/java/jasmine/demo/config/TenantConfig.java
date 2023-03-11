package jasmine.demo.config;

import jasmine.mybatis.tenant.IgnoreTableStrategy;
import jasmine.mybatis.tenant.TenantConfigProcessor;
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
