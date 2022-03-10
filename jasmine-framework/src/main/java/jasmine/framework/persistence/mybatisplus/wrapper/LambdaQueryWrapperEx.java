package jasmine.framework.persistence.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

/**
 * @author mh.z
 */
public class LambdaQueryWrapperEx<T> extends AbstractLambdaQueryWrapperEx<T> {

    public static <T> LambdaQueryWrapper<T> wrapper() {
        return new LambdaQueryWrapperEx<>();
    }

}
