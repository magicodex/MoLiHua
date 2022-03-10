package jasmine.framework.persistence.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author mh.z
 */
public class QueryWrapperEx<T> extends QueryWrapper<T> {

    public static <T> QueryWrapperEx<T> wrapper() {
        return new QueryWrapperEx<>();
    }

    @Override
    public QueryWrapperEx<T> eq(boolean condition, String column, Object val) {
        return (QueryWrapperEx<T>) super.eq(condition, column, val);
    }

    @Override
    public QueryWrapperEx<T> ne(boolean condition, String column, Object val) {
        return (QueryWrapperEx<T>) super.ne(condition, column, val);
    }

    @Override
    public QueryWrapperEx<T> gt(boolean condition, String column, Object val) {
        return (QueryWrapperEx<T>) super.gt(condition, column, val);
    }

    @Override
    public QueryWrapperEx<T> ge(boolean condition, String column, Object val) {
        return (QueryWrapperEx<T>) super.ge(condition, column, val);
    }

    @Override
    public QueryWrapperEx<T> lt(boolean condition, String column, Object val) {
        return (QueryWrapperEx<T>) super.lt(condition, column, val);
    }

    @Override
    public QueryWrapperEx<T> le(boolean condition, String column, Object val) {
        return (QueryWrapperEx<T>) super.le(condition, column, val);
    }

    @Override
    public QueryWrapperEx<T> like(boolean condition, String column, Object val) {
        return (QueryWrapperEx<T>) super.like(condition, column, val);
    }

    @Override
    public QueryWrapperEx<T> notLike(boolean condition, String column, Object val) {
        return (QueryWrapperEx<T>) super.notLike(condition, column, val);
    }

}
