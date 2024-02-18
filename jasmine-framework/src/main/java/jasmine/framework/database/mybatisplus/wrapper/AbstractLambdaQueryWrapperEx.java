package jasmine.framework.database.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mh.z
 */
public abstract class AbstractLambdaQueryWrapperEx<T, R extends LambdaQueryWrapper<T>> extends LambdaQueryWrapper<T> {

    public AbstractLambdaQueryWrapperEx() {
        super();
    }

    public AbstractLambdaQueryWrapperEx(T entity) {
        super(entity);
    }

    public AbstractLambdaQueryWrapperEx(Class<T> entityClass) {
        super(entityClass);
    }

    protected AbstractLambdaQueryWrapperEx(T entity, Class<T> entityClass, AtomicInteger paramNameSeq,
                                           Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments,
                                           SharedString paramAlias, SharedString lastSql,
                                           SharedString sqlComment, SharedString sqlFirst) {
        super.setEntity(entity);
        super.setEntityClass(entityClass);
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.paramAlias = paramAlias;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
        this.sqlFirst = sqlFirst;
    }

    @Override
    public R eq(SFunction<T, ?> column, Object val) {
        return (R) super.eq(column, val);
    }

    @Override
    public R ne(SFunction<T, ?> column, Object val) {
        return (R) super.ne(column, val);
    }

    @Override
    public R gt(SFunction<T, ?> column, Object val) {
        return (R) super.gt(column, val);
    }

    @Override
    public R ge(SFunction<T, ?> column, Object val) {
        return (R) super.ge(column, val);
    }

    @Override
    public R lt(SFunction<T, ?> column, Object val) {
        return (R) super.lt(column, val);
    }

    @Override
    public R le(SFunction<T, ?> column, Object val) {
        return (R) super.le(column, val);
    }


    @Override
    public R isNull(SFunction<T, ?> column) {
        return (R) super.isNull(column);
    }

    @Override
    public R isNotNull(SFunction<T, ?> column) {
        return (R) super.isNotNull(column);
    }

    @Override
    public R in(SFunction<T, ?> column, Collection<?> coll) {
        return (R) super.in(column, coll);
    }

    @Override
    public R in(SFunction<T, ?> column, Object... values) {
        return (R) super.in(column, values);
    }

    @Override
    public R notIn(SFunction<T, ?> column, Collection<?> coll) {
        return (R) super.notIn(column, coll);
    }

    @Override
    public R notIn(SFunction<T, ?> column, Object... value) {
        return (R) super.notIn(column, value);
    }

    @Override
    public R between(SFunction<T, ?> column, Object val1, Object val2) {
        return (R) super.between(column, val1, val2);
    }

    @Override
    public R notBetween(SFunction<T, ?> column, Object val1, Object val2) {
        return (R) super.notBetween(column, val1, val2);
    }

    @Override
    public R like(SFunction<T, ?> column, Object val) {
        return (R) super.like(column, val);
    }

    @Override
    public R notLike(SFunction<T, ?> column, Object val) {
        return (R) super.notLike(column, val);
    }

    @Override
    public R likeLeft(SFunction<T, ?> column, Object val) {
        return (R) super.likeLeft(column, val);
    }

    @Override
    public R likeRight(SFunction<T, ?> column, Object val) {
        return (R) super.likeRight(column, val);
    }

}
