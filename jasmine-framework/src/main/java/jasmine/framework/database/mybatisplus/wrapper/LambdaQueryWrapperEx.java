package jasmine.framework.database.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import jasmine.framework.database.mybatisplus.dynamic.DataAuthUtil;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mh.z
 */
public class LambdaQueryWrapperEx<T> extends AbstractLambdaQueryWrapperEx<T, LambdaQueryWrapperEx<T>> {

    public LambdaQueryWrapperEx() {
        super();
    }

    public LambdaQueryWrapperEx(T entity) {
        super(entity);
    }

    public LambdaQueryWrapperEx(Class<T> entityClass) {
        super(entityClass);
    }

    protected LambdaQueryWrapperEx(T entity, Class<T> entityClass, AtomicInteger paramNameSeq,
                                   Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments,
                                   SharedString paramAlias, SharedString lastSql,
                                   SharedString sqlComment, SharedString sqlFirst) {
        super(entity, entityClass, paramNameSeq, paramNameValuePairs, mergeSegments,
                paramAlias, lastSql, sqlComment, sqlFirst);
    }

    public static <T> LambdaQueryWrapperEx<T> wrapper() {
        return new LambdaQueryWrapperEx<>();
    }

    @Override
    protected LambdaQueryWrapperEx<T> instance() {
        return new LambdaQueryWrapperEx<>(getEntity(), getEntityClass(), paramNameSeq, paramNameValuePairs,
                new MergeSegments(), paramAlias, SharedString.emptyString(),
                SharedString.emptyString(), SharedString.emptyString());
    }

    /**
     * 数据权限
     *
     * @return
     */
    public LambdaQueryWrapper<T> dataAuth() {
        // 获取数据权限SQL
        Class<?> entityType = getEntityClass();
        String sql = DataAuthUtil.generateSql(entityType, null);
        // 加上数据权限SQL
        apply(sql);

        return this;
    }

}
