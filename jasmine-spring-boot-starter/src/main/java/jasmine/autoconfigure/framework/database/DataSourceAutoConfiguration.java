package jasmine.autoconfigure.framework.database;

import com.zaxxer.hikari.HikariDataSource;
import jasmine.framework.persistence.annotation.ReadOnlyAspectHandler;
import jasmine.framework.persistence.datasource.DataSourceDecideFacade;
import jasmine.framework.persistence.datasource.impl.MultipleDataSource;
import jasmine.framework.persistence.datasource.impl.ReadWriteDataSourceDecideFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author mh.z
 */
@AutoConfigureBefore({org.springframework.boot.autoconfigure.jdbc
        .DataSourceAutoConfiguration.class, MybatisPlusAutoConfiguration.class})
@ConditionalOnProperty(value = "jasmine.datasource.readWrite.enabled",
        havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties({DataSourceProperties.class, ReadDataSourceProperties.class})
@Configuration
public class DataSourceAutoConfiguration {

    @Bean
    public ReadOnlyAspectHandler readOnlyAspectHandler(
            @Autowired(required = false) DataSourceDecideFacade dataSourceDecideFacade) {
        return new ReadOnlyAspectHandler(dataSourceDecideFacade);
    }

    @Bean
    public DataSourceDecideFacade dataSourceDecideFacade() {
        return new ReadWriteDataSourceDecideFacade();
    }

    @Primary
    @Bean
    public DataSource dataSource(DataSourceProperties properties,
                                 ReadDataSourceProperties readProperties) {
        DataSource mainDataSource = mainDataSource(properties);
        DataSource readDataSource = readDataSource(properties, readProperties);

        Map<Object, Object> dataSourceMap = Map.of("master", mainDataSource,
                "read", readDataSource);
        MultipleDataSource multipleDataSource = new MultipleDataSource(dataSourceMap);
        multipleDataSource.setDefaultTargetDataSource(mainDataSource);

        return multipleDataSource;
    }

    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Bean
    public HikariDataSource mainDataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();

        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }

        return dataSource;
    }

    @ConfigurationProperties(prefix = "spring.datasource.hikari.read")
    @Bean
    public HikariDataSource readDataSource(DataSourceProperties properties,
                                           ReadDataSourceProperties readProperties) {
        HikariDataSource dataSource = DataSourceBuilder.create(properties.getClassLoader())
                .driverClassName(readProperties.getDriverClassName())
                .url(readProperties.getUrl())
                .username(readProperties.getUsername())
                .password(readProperties.getPassword())
                .type(HikariDataSource.class)
                .build();
        dataSource.setPoolName("read");

        return dataSource;
    }


}
