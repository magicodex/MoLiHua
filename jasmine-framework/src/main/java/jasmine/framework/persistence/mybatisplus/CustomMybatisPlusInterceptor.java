package jasmine.framework.persistence.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class CustomMybatisPlusInterceptor extends MybatisPlusInterceptor {

    public CustomMybatisPlusInterceptor() {
        addInnerInterceptor(new OptimisticLockerInnerInterceptor());
    }

}
