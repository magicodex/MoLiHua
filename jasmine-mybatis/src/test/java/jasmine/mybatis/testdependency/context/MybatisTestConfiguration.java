package jasmine.mybatis.testdependency.context;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import jasmine.mybatis.crypto.CryptoProvider;
import jasmine.mybatis.BaseEntityMetaObjectHandler;
import jasmine.mybatis.MybatisPlusInterceptorBuilder;
import jasmine.mybatis.crypto.CryptoFieldUtil;
import jasmine.mybatis.crypto.SymmetricCryptoProvider;
import jasmine.mybatis.i18n.DefaultI18nEntityFacade;
import jasmine.mybatis.i18n.I18nEntityFacade;
import jasmine.mybatis.i18n.I18nEntityUtil;
import jasmine.mybatis.tenant.DefaultTenantLineHandler;
import jasmine.mybatis.tenant.IgnoreTableStrategy;
import jasmine.mybatis.tenant.TenantConfigProcessorScanBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author mh.z
 */
@Configuration
public class MybatisTestConfiguration {
    @Value("${jasmine.data.tenant.enabled:false}")
    private Boolean tenantEnabled;

    @Value("${jasmine.mybatis.mapper.path:}")
    private String mapperPath;

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();

        // 数据源
        factoryBean.setDataSource(dataSource);
        // 拦截器
        factoryBean.setPlugins(mybatisPlusInterceptor());
        // mapper文件路径
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = pathResolver.getResources(mapperPath);
        factoryBean.setMapperLocations(resources);

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
        CryptoProvider provider = new SymmetricCryptoProvider("secret", "salt");
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
    public MybatisTestInitializingBean customInitializingSingletonScanBean() {
        return new MybatisTestInitializingBean();
    }

    @Bean
    public TenantConfigProcessorScanBean tenantConfigProcessorScanBean() {
        IgnoreTableStrategy ignoreTableStrategy = tenantLineHandler();

        return new TenantConfigProcessorScanBean(ignoreTableStrategy);
    }

    @Bean
    public DefaultTenantLineHandler tenantLineHandler() {
        DefaultTenantLineHandler handler = new DefaultTenantLineHandler();

        return handler;
    }

}
