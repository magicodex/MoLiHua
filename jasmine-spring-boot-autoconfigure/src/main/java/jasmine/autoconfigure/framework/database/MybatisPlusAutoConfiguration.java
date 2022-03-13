package jasmine.autoconfigure.framework.database;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import jasmine.framework.common.crypto.CryptoProvider;
import jasmine.framework.persistence.mybatisplus.BaseEntityMetaObjectHandler;
import jasmine.framework.persistence.mybatisplus.MybatisPlusInterceptorBuilder;;
import jasmine.framework.persistence.mybatisplus.crypto.CryptoFieldHelper;
import jasmine.framework.persistence.mybatisplus.crypto.SymmetricCryptoProvider;
import jasmine.framework.persistence.mybatisplus.tenant.DefaultTenantLineHandler;
import jasmine.framework.persistence.mybatisplus.tenant.IgnoreTableStrategy;
import jasmine.framework.persistence.mybatisplus.tenant.TenantConfigProcessorScanBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@EnableConfigurationProperties(MybatisPlusProperties.class)
@Configuration
public class MybatisPlusAutoConfiguration {

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

}
