package jasmine.framework.persistence.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
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

    public MybatisPlusInterceptorBuilder(SubjectProvider subjectProvider) {
        this.subjectProvider = subjectProvider;
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

        ContextParameter contextParameter = new DefaultContextParameter(subjectProvider);
        interceptor.addInnerInterceptor(new ContextParameterInnerInterceptor(contextParameter));

        return interceptor;
    }

}
