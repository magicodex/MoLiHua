package jasmine.testconfigure.framework;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import jasmine.framework.database.mybatisplus.BaseEntityMetaObjectHandler;
import jasmine.framework.database.mybatisplus.MybatisPlusInterceptorBuilder;
import jasmine.framework.database.mybatisplus.crypto.CryptoFieldUtil;
import jasmine.framework.database.mybatisplus.crypto.CryptoProvider;
import jasmine.framework.database.mybatisplus.i18n.DefaultI18nEntityFacade;
import jasmine.framework.database.mybatisplus.i18n.I18nEntityFacade;
import jasmine.framework.database.mybatisplus.i18n.I18nEntityUtil;
import jasmine.framework.database.mybatisplus.tenant.DefaultTenantLineHandler;
import jasmine.framework.database.mybatisplus.tenant.IgnoreTableStrategy;
import jasmine.framework.database.mybatisplus.tenant.TenantConfigProcessorScanBean;
import jasmine.mock.framework.database.MockCryptoProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author mh.z
 */
@Configuration
public class MybatisPlusTestConfiguration {
    @Value("${jasmine.data.tenant.enabled:false}")
    private Boolean tenantEnabled;

    @Value("${mybatis-plus.mapperLocations:classpath*:/jasmine/**/mapper/*.xml}")
    private String[] mapperLocations;

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();

        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        Resource[][] resourceArray = new Resource[mapperLocations.length][];
        for (int index = 0; index < mapperLocations.length; index++) {
            resourceArray[index] = resourceResolver.getResources(mapperLocations[index]);
        }

        // 数据源
        factoryBean.setDataSource(dataSource);
        // 拦截器
        factoryBean.setPlugins(mybatisPlusInterceptor());
        // mapper文件路径
        Resource[] mapperResources = ArrayUtil.addAll(resourceArray);
        factoryBean.setMapperLocations(mapperResources);

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
        CryptoFieldUtil.initUtil(provider);

        return provider;
    }

    @Bean
    public I18nEntityFacade i18nEntityFacade(SqlSessionTemplate sqlSessionTemplate) {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);
        // 初始工具类
        I18nEntityUtil.initUtil(facade);

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
