package jasmine.mybatis;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import jasmine.core.util.CheckUtil;
import jasmine.mybatis.context.ContextParameter;
import jasmine.mybatis.context.ContextParameterInnerInterceptor;
import jasmine.mybatis.context.DefaultContextParameter;

/**
 * @author mh.z
 */
public class MybatisPlusInterceptorBuilder {
    private Boolean tenantEnabled;
    private TenantLineHandler tenantLineHandler;

    public void setTenantEnabled(Boolean tenantEnabled) {
        this.tenantEnabled = tenantEnabled;
    }

    public void setTenantLineHandler(TenantLineHandler tenantLineHandler) {
        this.tenantLineHandler = tenantLineHandler;
    }

    /**
     * 创建
     *
     * @return
     */
    public MybatisPlusInterceptor build() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 如果用了分页插件注意先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
        // 用了分页插件必须设置 MybatisConfiguration#useDeprecatedExecutor = false

        // 租户拦截器
        if (Boolean.TRUE.equals(tenantEnabled)) {
            CheckUtil.notNullProp(tenantLineHandler, "tenantLineHandler null");
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(tenantLineHandler));
        }

        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        // 防止全表更新与删除插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

        ContextParameter contextParameter = new DefaultContextParameter();
        interceptor.addInnerInterceptor(new ContextParameterInnerInterceptor(contextParameter));

        return interceptor;
    }

}
