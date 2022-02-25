package jasmine.mock.testconfigure;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import jasmine.core.context.SubjectProvider;
import jasmine.framework.persistence.mybatisplus.BaseEntityMetaObjectHandler;
import jasmine.framework.persistence.mybatisplus.ContextParameter;
import jasmine.framework.persistence.mybatisplus.ContextParameterInnerInterceptor;
import jasmine.framework.persistence.mybatisplus.DefaultContextParameter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class MybatisPlusTestConfiguration {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(SubjectProvider subjectProvider) {
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

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new BaseEntityMetaObjectHandler();
    }

}
