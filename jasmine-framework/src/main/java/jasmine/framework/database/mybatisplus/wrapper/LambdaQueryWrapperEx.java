package jasmine.framework.database.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jasmine.framework.database.mybatisplus.dynamic.DataAuthUtil;

/**
 * @author mh.z
 */
public class LambdaQueryWrapperEx<T> extends AbstractLambdaQueryWrapperEx<T, LambdaQueryWrapperEx<T>> {

    public static <T> LambdaQueryWrapperEx<T> wrapper() {
        return new LambdaQueryWrapperEx<>();
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
