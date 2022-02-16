package jasmine.framework.persistence.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import jasmine.core.context.SubjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mh.z
 */
@Configuration
public class MybatisPlusInterceptorConfig {
    private SubjectProvider subjectProvider;

    public MybatisPlusInterceptorConfig(@Autowired(required = false) SubjectProvider subjectProvider) {
        this.subjectProvider = subjectProvider;
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptorBean = new MybatisPlusInterceptor();
        interceptorBean.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        ContextParameter contextParameter = new DefaultContextParameter(subjectProvider);
        interceptorBean.addInnerInterceptor(new ContextParameterInnerInterceptor(contextParameter));

        return interceptorBean;
    }

}
