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

    public QueryWrapperEx(T entity, Class<T> entityClass, AtomicInteger paramNameSeq,
                          Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments, SharedString paramAlias,
                          SharedString lastSql, SharedString sqlComment, SharedString sqlFirst) {
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

    public static <T> QueryWrapperEx<T> wrapper() {
        return new QueryWrapperEx<>();
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
