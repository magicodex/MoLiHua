package jasmine.autoconfigure.framework.persistence;

import jasmine.framework.persistence.datasource.DataSourceDecideFacade;
import jasmine.framework.persistence.datasource.aspect.ReadOnlyAspectHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class DataSourceAutoConfiguration {

    @Bean
    public ReadOnlyAspectHandler readOnlyAspectHandler(@Autowired(required = false) DataSourceDecideFacade dataSourceDecideFacade) {
        return new ReadOnlyAspectHandler(dataSourceDecideFacade);
    }

}
