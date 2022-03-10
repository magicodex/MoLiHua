package jasmine.framework.persistence.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

/**
 * @author mh.z
 */
public abstract class AbstractLambdaQueryWrapperEx<T> extends LambdaQueryWrapper<T> {

    @Override
    public AbstractLambdaQueryWrapperEx<T> eq(boolean condition, SFunction<T, ?> column, Object val) {
        return (AbstractLambdaQueryWrapperEx<T>) super.eq(condition, column, val);
    }

    @Override
    public AbstractLambdaQueryWrapperEx<T> ne(boolean condition, SFunction<T, ?> column, Object val) {
        return (AbstractLambdaQueryWrapperEx<T>) super.ne(condition, column, val);
    }

    @Override
    public AbstractLambdaQueryWrapperEx<T> gt(boolean condition, SFunction<T, ?> column, Object val) {
        return (AbstractLambdaQueryWrapperEx<T>) super.gt(condition, column, val);
    }

    @Override
    public AbstractLambdaQueryWrapperEx<T> ge(boolean condition, SFunction<T, ?> column, Object val) {
        return (AbstractLambdaQueryWrapperEx<T>) super.ge(condition, column, val);
    }

    @Override
    public AbstractLambdaQueryWrapperEx<T> lt(boolean condition, SFunction<T, ?> column, Object val) {
        return (AbstractLambdaQueryWrapperEx<T>) super.lt(condition, column, val);
    }

    @Override
    public AbstractLambdaQueryWrapperEx<T> le(boolean condition, SFunction<T, ?> column, Object val) {
        return (AbstractLambdaQueryWrapperEx<T>) super.le(condition, column, val);
    }

    @Override
    public AbstractLambdaQueryWrapperEx<T> like(boolean condition, SFunction<T, ?> column, Object val) {
        return (AbstractLambdaQueryWrapperEx<T>) super.like(condition, column, val);
    }

    @Override
    public AbstractLambdaQueryWrapperEx<T> notLike(boolean condition, SFunction<T, ?> column, Object val) {
        return (AbstractLambdaQueryWrapperEx<T>) super.notLike(condition, column, val);
    }

}
