package jasmine.framework.database.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mh.z
 */
public abstract class AbstractQueryWrapperEx<T, R extends QueryWrapper<T>> extends QueryWrapper<T> {

    public AbstractQueryWrapperEx() {
        super();
    }

    public AbstractQueryWrapperEx(T entity) {
        super(entity);
    }

    public AbstractQueryWrapperEx(T entity, String... columns) {
        super(entity, columns);
    }

    protected AbstractQueryWrapperEx(T entity, Class<T> entityClass, AtomicInteger paramNameSeq,
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
    public R eq(boolean condition, String column, Object val) {
        return (R) super.eq(condition, column, val);
    }

    @Override
    public R ne(boolean condition, String column, Object val) {
        return (R) super.ne(condition, column, val);
    }

    @Override
    public R gt(boolean condition, String column, Object val) {
        return (R) super.gt(condition, column, val);
    }

    @Override
    public R ge(boolean condition, String column, Object val) {
        return (R) super.ge(condition, column, val);
    }

    @Override
    public R lt(boolean condition, String column, Object val) {
        return (R) super.lt(condition, column, val);
    }

    @Override
    public R le(boolean condition, String column, Object val) {
        return (R) super.le(condition, column, val);
    }

    @Override
    public R isNull(boolean condition, String column) {
        return (R) super.isNull(condition, column);
    }

    @Override
    public R isNotNull(boolean condition, String column) {
        return (R) super.isNotNull(condition, column);
    }

    @Override
    public R in(boolean condition, String column, Collection<?> coll) {
        return (R) super.in(condition, column, coll);
    }

    @Override
    public R in(boolean condition, String column, Object... values) {
        return (R) super.in(condition, column, values);
    }

    @Override
    public R notIn(boolean condition, String column, Collection<?> coll) {
        return (R) super.notIn(condition, column, coll);
    }

    @Override
    public R notIn(boolean condition, String column, Object... values) {
        return (R) super.notIn(condition, column, values);
    }

    @Override
    public R between(boolean condition, String column, Object val1, Object val2) {
        return (R) super.between(condition, column, val1, val2);
    }

    @Override
    public R notBetween(boolean condition, String column, Object val1, Object val2) {
        return (R) super.notBetween(condition, column, val1, val2);
    }

    @Override
    public R like(boolean condition, String column, Object val) {
        return (R) super.like(condition, column, val);
    }

    @Override
    public R notLike(boolean condition, String column, Object val) {
        return (R) super.notLike(condition, column, val);
    }

    @Override
    public R likeLeft(boolean condition, String column, Object val) {
        return (R) super.likeLeft(condition, column, val);
    }

    @Override
    public R likeRight(boolean condition, String column, Object val) {
        return (R) super.likeRight(condition, column, val);
    }

}
