package jasmine.demo.config;

import jasmine.framework.mybatis.tenant.IgnoreTableStrategy;
import jasmine.framework.mybatis.tenant.TenantConfigProcessor;
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
