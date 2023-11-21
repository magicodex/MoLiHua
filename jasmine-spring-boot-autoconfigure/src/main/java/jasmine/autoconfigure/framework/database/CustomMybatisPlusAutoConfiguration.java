package jasmine.autoconfigure.framework.database;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import jasmine.framework.context.CurrentSubject;
import jasmine.framework.context.impl.FixedSubjectProvider;
import jasmine.framework.database.mybatisplus.BaseEntityMetaObjectHandler;
import jasmine.framework.database.mybatisplus.MybatisPlusInterceptorBuilder;
import jasmine.framework.database.mybatisplus.crypto.CryptoFieldUtil;
import jasmine.framework.database.mybatisplus.crypto.CryptoProvider;
import jasmine.framework.database.mybatisplus.crypto.SymmetricCryptoProvider;
import jasmine.framework.database.mybatisplus.i18n.DefaultI18nEntityFacade;
import jasmine.framework.database.mybatisplus.i18n.I18nEntityFacade;
import jasmine.framework.database.mybatisplus.i18n.I18nEntityUtil;
import jasmine.framework.database.mybatisplus.i18n.injector.I18nSupportSqlInjector;
import jasmine.framework.database.mybatisplus.tenant.DefaultTenantLineHandler;
import jasmine.framework.database.mybatisplus.tenant.IgnoreTableStrategy;
import jasmine.framework.database.mybatisplus.tenant.TenantConfigProcessorScanBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@AutoConfigureBefore({MybatisPlusAutoConfiguration.class})
@ConditionalOnClass(MybatisConfiguration.class)
@EnableConfigurationProperties(DataProperties.class)
@Configuration
public class CustomMybatisPlusAutoConfiguration implements SmartInitializingSingleton {

    @ConditionalOnMissingBean(CryptoProvider.class)
    @Bean
    public CryptoProvider cryptoProvider(DataProperties dataProperties) {
        DataProperties.Crypto crypto = dataProperties.getCrypto();
        String password = crypto.getPassword();
        String salt = crypto.getSalt();
        SymmetricCryptoProvider provider = new SymmetricCryptoProvider(password, salt);

        // 初始工具类
        CryptoFieldUtil.initUtil(provider);

        return provider;
    }

    @ConditionalOnMissingBean(I18nEntityFacade.class)
    @Bean
    public I18nEntityFacade i18nEntityFacade(SqlSessionTemplate sqlSessionTemplate) {
        DefaultI18nEntityFacade facade = new DefaultI18nEntityFacade(sqlSessionTemplate);

        // 初始工具类
        I18nEntityUtil.initUtil(facade);

        return facade;
    }

    @ConditionalOnMissingBean(ISqlInjector.class)
    @Bean
    public I18nSupportSqlInjector sqlInjector() {
        return new I18nSupportSqlInjector();
    }

    @ConditionalOnMissingBean(TenantLineHandler.class)
    @Bean
    public DefaultTenantLineHandler tenantLineHandler() {
        return new DefaultTenantLineHandler();
    }

    @ConditionalOnMissingBean(MybatisPlusInterceptor.class)
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(DataProperties dataProperties,
                                                         TenantLineHandler tenantLineHandler) {
        Boolean tenantEnabled = dataProperties.getTenant().getEnabled();

        MybatisPlusInterceptorBuilder builder = new MybatisPlusInterceptorBuilder();
        // 是否启用租户拦截器
        builder.setTenantEnabled(Boolean.TRUE.equals(tenantEnabled));
        builder.setTenantLineHandler(tenantLineHandler);

        return builder.build();
    }

    @ConditionalOnMissingBean(MetaObjectHandler.class)
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new BaseEntityMetaObjectHandler();
    }

    /**
     * 支持自定义租户配置
     *
     * @return
     */
    @ConditionalOnMissingBean(TenantConfigProcessorScanBean.class)
    @Bean
    public TenantConfigProcessorScanBean tenantConfigProcessorScanBean() {
        IgnoreTableStrategy ignoreTableStrategy = tenantLineHandler();

        return new TenantConfigProcessorScanBean(ignoreTableStrategy);
    }

    @Override
    public void afterSingletonsInstantiated() {
        if (!CurrentSubject.isInitialized()) {
            CurrentSubject.initUtil(new FixedSubjectProvider());
        }
    }

}
