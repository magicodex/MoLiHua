package jasmine.framework.persistence.mybatisplus.wrapper;

import jasmine.framework.persistence.mybatisplus.dynamic.DataAuthHelper;

/**
 * @author mh.z
 */
public class QueryWrapperEx<T> extends AbstractQueryWrapperEx<T, QueryWrapperEx<T>> {

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
        String sql = DataAuthHelper.generateSql(entityType, null);
        // 加上数据权限SQL
        apply(sql);

        return this;
    }

}
