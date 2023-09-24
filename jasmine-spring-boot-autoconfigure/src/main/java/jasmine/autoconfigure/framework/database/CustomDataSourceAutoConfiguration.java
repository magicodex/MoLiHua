package jasmine.autoconfigure.framework.database;

import com.zaxxer.hikari.HikariDataSource;
import jasmine.framework.common.util.NewUtil;
import jasmine.framework.database.annotation.handler.ReadOnlyAspectHandler;
import jasmine.framework.database.datasource.DataSourceDecideFacade;
import jasmine.framework.database.impl.datasource.MultipleDataSource;
import jasmine.framework.database.impl.datasource.ReadWriteDataSourceDecideFacade;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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
@AutoConfigureBefore({DataSourceAutoConfiguration.class, CustomMybatisPlusAutoConfiguration.class})
@ConditionalOnProperty(value = "jasmine.datasource.readWrite.enabled",
        havingValue = "true", matchIfMissing = false)
@EnableConfigurationProperties({DataSourceProperties.class, ReadDataSourceProperties.class})
@Configuration
public class CustomDataSourceAutoConfiguration {
    private static final String MAIN_DATA_SOURCE_NAME = "master";
    private static final String READ_DATA_SOURCE_NAME = "read";
    private static final String READ_CONNECTION_POOL_NAME = "read";

    @ConditionalOnMissingBean(DataSourceDecideFacade.class)
    @Bean
    public DataSourceDecideFacade dataSourceDecideFacade() {
        return new ReadWriteDataSourceDecideFacade();
    }

    @ConditionalOnMissingBean(ReadOnlyAspectHandler.class)
    @Bean
    public ReadOnlyAspectHandler readOnlyAspectHandler(DataSourceDecideFacade dataSourceDecideFacade) {
        return new ReadOnlyAspectHandler(dataSourceDecideFacade);
    }

    /**
     * 默认数据源
     *
     * @param properties
     * @return
     */
    @ConditionalOnMissingBean(name = "mainDataSource")
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

    /**
     * 只读数据源
     *
     * @param properties
     * @param readProperties
     * @return
     */
    @ConditionalOnMissingBean(name = "readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.read")
    @Bean
    public HikariDataSource readDataSource(DataSourceProperties properties,
                                           ReadDataSourceProperties readProperties) {
        HikariDataSource dataSource = DataSourceBuilder.create(properties.getClassLoader())
                .driverClassName(properties.getDriverClassName())
                .url(readProperties.getUrl())
                .username(readProperties.getUsername())
                .password(readProperties.getPassword())
                .type(HikariDataSource.class)
                .build();
        dataSource.setPoolName(READ_CONNECTION_POOL_NAME);
        dataSource.setReadOnly(true);

        return dataSource;
    }

    @Primary
    @Bean
    public DataSource dataSource(DataSource mainDataSource,
                                 DataSource readDataSource) {
        Map<Object, Object> dataSourceMap = NewUtil.asMap(MAIN_DATA_SOURCE_NAME, mainDataSource,
                READ_DATA_SOURCE_NAME, readDataSource);
        MultipleDataSource multipleDataSource = new MultipleDataSource(dataSourceMap);
        multipleDataSource.setDefaultTargetDataSource(mainDataSource);

        return multipleDataSource;
    }

}
