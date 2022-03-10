package jasmine.framework.persistence.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * @author mh.z
 */
public class LambdaQueryWrapperEx<T> extends LambdaQueryWrapper<T> {

    public static <T> LambdaQueryWrapper<T> wrapper() {
        return new LambdaQueryWrapperEx<>();
    }

    @Override
    public LambdaQueryWrapperEx<T> eq(boolean condition, SFunction<T, ?> column, Object val) {
        return (LambdaQueryWrapperEx<T>) super.eq(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperEx<T> ne(boolean condition, SFunction<T, ?> column, Object val) {
        return (LambdaQueryWrapperEx<T>) super.ne(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperEx<T> gt(boolean condition, SFunction<T, ?> column, Object val) {
        return (LambdaQueryWrapperEx<T>) super.gt(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperEx<T> ge(boolean condition, SFunction<T, ?> column, Object val) {
        return (LambdaQueryWrapperEx<T>) super.ge(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperEx<T> lt(boolean condition, SFunction<T, ?> column, Object val) {
        return (LambdaQueryWrapperEx<T>) super.lt(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperEx<T> le(boolean condition, SFunction<T, ?> column, Object val) {
        return (LambdaQueryWrapperEx<T>) super.le(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperEx<T> like(boolean condition, SFunction<T, ?> column, Object val) {
        return (LambdaQueryWrapperEx<T>) super.like(condition, column, val);
    }

    @Override
    public LambdaQueryWrapperEx<T> notLike(boolean condition, SFunction<T, ?> column, Object val) {
        return (LambdaQueryWrapperEx<T>) super.notLike(condition, column, val);
    }

}
