package jasmine.autoconfigure.framework.database;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import jasmine.core.context.CurrentSubject;
import jasmine.framework.common.security.CryptoProvider;
import jasmine.framework.context.FixedSubjectProvider;
import jasmine.framework.persistence.mybatisplus.BaseEntityMetaObjectHandler;
import jasmine.framework.persistence.mybatisplus.MybatisPlusInterceptorBuilder;
import jasmine.framework.persistence.mybatisplus.crypto.CryptoFieldHelper;
import jasmine.framework.persistence.mybatisplus.crypto.SymmetricCryptoProvider;
import jasmine.framework.persistence.mybatisplus.i18n.DefaultI18nEntityFacade;
import jasmine.framework.persistence.mybatisplus.i18n.I18nEntityFacade;
import jasmine.framework.persistence.mybatisplus.i18n.I18nEntityHelper;
import jasmine.framework.persistence.mybatisplus.tenant.DefaultTenantLineHandler;
import jasmine.framework.persistence.mybatisplus.tenant.IgnoreTableStrategy;
import jasmine.framework.persistence.mybatisplus.tenant.TenantConfigProcessorScanBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@ConditionalOnClass(MybatisConfiguration.class)
@EnableConfigurationProperties(MybatisPlusProperties.class)
@Configuration
public class MybatisPlusAutoConfiguration implements SmartInitializingSingleton {

    @Bean
    public CryptoProvider cryptoProvider(MybatisPlusProperties mybatisPlusProperties) {
        MybatisPlusProperties.Crypto crypto = mybatisPlusProperties.getCrypto();
        String password = crypto.getPassword();
        String salt = crypto.getSalt();
        SymmetricCryptoProvider provider = new SymmetricCryptoProvider(password, salt);

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
    public DefaultTenantLineHandler tenantLineHandler() {
        return new DefaultTenantLineHandler();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(MybatisPlusProperties mybatisPlusProperties,
                                                         TenantLineHandler tenantLineHandler) {
        Boolean tenantEnabled = mybatisPlusProperties.getTenant().getEnabled();

        MybatisPlusInterceptorBuilder builder = new MybatisPlusInterceptorBuilder();
        // 是否启用租户拦截器
        builder.setTenantEnabled(Boolean.TRUE.equals(tenantEnabled));
        builder.setTenantLineHandler(tenantLineHandler);

        return builder.build();
    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new BaseEntityMetaObjectHandler();
    }

    /**
     * 支持自定义租户配置
     *
     * @return
     */
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
