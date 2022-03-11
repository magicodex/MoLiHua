package jasmine.mock.testconfigure.database;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import jasmine.framework.persistence.mybatisplus.BaseEntityMetaObjectHandler;
import jasmine.framework.persistence.mybatisplus.MybatisPlusInterceptorBuilder;
import jasmine.framework.persistence.mybatisplus.crypto.CryptoProvider;
import jasmine.framework.persistence.mybatisplus.crypto.CryptoTypeHandler;
import jasmine.framework.persistence.mybatisplus.tenant.DefaultTenantLineHandler;
import jasmine.framework.persistence.mybatisplus.tenant.IgnoreTableStrategy;
import jasmine.framework.persistence.mybatisplus.tenant.TenantConfigProcessorScanBean;
import jasmine.mock.persistence.MockCryptoProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * @author mh.z
 */
@Configuration
public class MybatisPlusTestConfiguration {
    @Value("${jasmine.data.tenant.enabled:false}")
    private Boolean tenantEnabled;

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();

        // 数据源
        factoryBean.setDataSource(dataSource);
        // 拦截器
        factoryBean.setPlugins(mybatisPlusInterceptor());
        // mapper文件路径
        ClassPathResource mapperLocation = new ClassPathResource("jasmine/framework/mapper/DataAuthMapper.xml");
        factoryBean.setMapperLocations(mapperLocation);

        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
        globalConfig.setMetaObjectHandler(metaObjectHandler());
        factoryBean.setGlobalConfig(globalConfig);

        return factoryBean;
    }

    @Bean
    public CryptoProvider cryptoProvider() {
        CryptoProvider provider = new MockCryptoProvider();
        // 初始加密的类型处理器
        CryptoTypeHandler.setCryptoProvider(provider);

        return provider;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptorBuilder builder = new MybatisPlusInterceptorBuilder();
        builder.setTenantEnabled(Boolean.TRUE.equals(tenantEnabled));
        builder.setTenantLineHandler(tenantLineHandler());

        return builder.build();
    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new BaseEntityMetaObjectHandler();
    }

    @Bean
    public TenantConfigProcessorScanBean tenantConfigProcessorScanBean() {
        IgnoreTableStrategy ignoreTableStrategy = tenantLineHandler();

        return new TenantConfigProcessorScanBean(ignoreTableStrategy);
    }

    @Bean
    public DefaultTenantLineHandler tenantLineHandler() {
        DefaultTenantLineHandler handler = new DefaultTenantLineHandler();
        handler.addIgnoreTable("test_data_change_log");

        return handler;
    }

}
