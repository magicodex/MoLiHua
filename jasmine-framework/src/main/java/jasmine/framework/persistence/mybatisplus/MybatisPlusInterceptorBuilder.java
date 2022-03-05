package jasmine.framework.persistence.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import jasmine.core.context.SubjectProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.framework.persistence.mybatisplus.context.ContextParameter;
import jasmine.framework.persistence.mybatisplus.context.ContextParameterInnerInterceptor;
import jasmine.framework.persistence.mybatisplus.context.DefaultContextParameter;

/**
 * @author mh.z
 */
public class MybatisPlusInterceptorBuilder {
    private SubjectProvider subjectProvider;
    private Boolean tenantEnabled;
    private TenantLineHandler tenantLineHandler;

    public MybatisPlusInterceptorBuilder(SubjectProvider subjectProvider) {
        this.subjectProvider = subjectProvider;
    }

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
        QCheckUtil.notNullProp(subjectProvider, "subjectProvider null");

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        // 防止全表更新与删除插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

        // 租户拦截器
        if (Boolean.TRUE.equals(tenantEnabled)) {
            QCheckUtil.notNullProp(tenantLineHandler, "tenantLineHandler null");
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(tenantLineHandler));
        }

        ContextParameter contextParameter = new DefaultContextParameter(subjectProvider);
        interceptor.addInnerInterceptor(new ContextParameterInnerInterceptor(contextParameter));

        return interceptor;
    }

}
