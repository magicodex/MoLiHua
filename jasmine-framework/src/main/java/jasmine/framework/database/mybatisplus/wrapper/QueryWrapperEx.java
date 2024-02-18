package jasmine.framework.database.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import jasmine.framework.database.mybatisplus.dynamic.DataAuthUtil;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mh.z
 */
public class QueryWrapperEx<T> extends AbstractQueryWrapperEx<T, QueryWrapperEx<T>> {

    public QueryWrapperEx() {
        super();
    }

    public QueryWrapperEx(T entity) {
        super(entity);
    }

    public QueryWrapperEx(T entity, String... columns) {
        super(entity, columns);
    }

    protected QueryWrapperEx(T entity, Class<T> entityClass, AtomicInteger paramNameSeq,
                             Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments,
                             SharedString paramAlias, SharedString lastSql,
                             SharedString sqlComment, SharedString sqlFirst) {
        super(entity, entityClass, paramNameSeq, paramNameValuePairs, mergeSegments,
                paramAlias, lastSql, sqlComment, sqlFirst);
    }

    public static <T> QueryWrapperEx<T> wrapper() {
        return new QueryWrapperEx<>();
    }

    @Override
    protected QueryWrapperEx<T> instance() {
        return new QueryWrapperEx<>(getEntity(), getEntityClass(), paramNameSeq, paramNameValuePairs,
                new MergeSegments(), paramAlias, SharedString.emptyString(),
                SharedString.emptyString(), SharedString.emptyString());
    }

    /**
     * 数据权限
     *
     * @return
     */
    public QueryWrapperEx<T> dataAuth() {
        // 获取数据权限SQL
        Class<?> entityType = getEntityClass();
        String sql = DataAuthUtil.generateSql(entityType, null);
        // 加上数据权限SQL
        apply(sql);

        return this;
    }

}
