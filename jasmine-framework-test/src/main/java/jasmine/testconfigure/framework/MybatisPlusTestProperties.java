package jasmine.testconfigure.framework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class MybatisPlusTestProperties {

    @Value("${jasmine.data.tenant.enabled:false}")
    private Boolean tenantEnabled;

    @Value("${mybatis-plus.mapperLocations:classpath*:/jasmine/**/mapper/*.xml}")
    private String[] mapperLocations;

    public Boolean getTenantEnabled() {
        return tenantEnabled;
    }

    public String[] getMapperLocations() {
        return mapperLocations;
    }

}
