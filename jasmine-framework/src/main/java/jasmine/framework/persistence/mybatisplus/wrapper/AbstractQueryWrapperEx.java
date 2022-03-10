package jasmine.framework.persistence.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author mh.z
 */
public abstract class AbstractQueryWrapperEx<T> extends QueryWrapper<T> {

    @Override
    public AbstractQueryWrapperEx<T> eq(boolean condition, String column, Object val) {
        return (AbstractQueryWrapperEx<T>) super.eq(condition, column, val);
    }

    @Override
    public AbstractQueryWrapperEx<T> ne(boolean condition, String column, Object val) {
        return (AbstractQueryWrapperEx<T>) super.ne(condition, column, val);
    }

    @Override
    public AbstractQueryWrapperEx<T> gt(boolean condition, String column, Object val) {
        return (AbstractQueryWrapperEx<T>) super.gt(condition, column, val);
    }

    @Override
    public AbstractQueryWrapperEx<T> ge(boolean condition, String column, Object val) {
        return (AbstractQueryWrapperEx<T>) super.ge(condition, column, val);
    }

    @Override
    public AbstractQueryWrapperEx<T> lt(boolean condition, String column, Object val) {
        return (AbstractQueryWrapperEx<T>) super.lt(condition, column, val);
    }

    @Override
    public AbstractQueryWrapperEx<T> le(boolean condition, String column, Object val) {
        return (AbstractQueryWrapperEx<T>) super.le(condition, column, val);
    }

    @Override
    public AbstractQueryWrapperEx<T> like(boolean condition, String column, Object val) {
        return (AbstractQueryWrapperEx<T>) super.like(condition, column, val);
    }

    @Override
    public AbstractQueryWrapperEx<T> notLike(boolean condition, String column, Object val) {
        return (AbstractQueryWrapperEx<T>) super.notLike(condition, column, val);
    }

}
