package jasmine.testconfigure.framework.database;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import jasmine.framework.common.security.CryptoProvider;
import jasmine.mock.framework.common.MockCryptoProvider;
import jasmine.framework.persistence.mybatisplus.BaseEntityMetaObjectHandler;
import jasmine.framework.persistence.mybatisplus.MybatisPlusInterceptorBuilder;
import jasmine.framework.persistence.mybatisplus.crypto.CryptoFieldHelper;
import jasmine.framework.persistence.mybatisplus.i18n.DefaultI18nEntityFacade;
import jasmine.framework.persistence.mybatisplus.i18n.I18nEntityFacade;
import jasmine.framework.persistence.mybatisplus.i18n.I18nEntityHelper;
import jasmine.framework.persistence.mybatisplus.tenant.DefaultTenantLineHandler;
import jasmine.framework.persistence.mybatisplus.tenant.IgnoreTableStrategy;
import jasmine.framework.persistence.mybatisplus.tenant.TenantConfigProcessorScanBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
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
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public CryptoProvider cryptoProvider() {
        CryptoProvider provider = new MockCryptoProvider();
        // 初始工具类
        CryptoFieldHelper.initUtil(provider);

        return provider;
    }

    @Bean
    public I18nEntityFacade i18nEntityFacade(SqlSessionTemplate sqlSessionTemplate) {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);

        // 初始工具类
        I18nEntityHelper.initUtil(facade);

        return facade;
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
        // 租户拦截器不处理以下的表
        handler.addIgnoreTable("test_data_change_log");

        return handler;
    }

}
