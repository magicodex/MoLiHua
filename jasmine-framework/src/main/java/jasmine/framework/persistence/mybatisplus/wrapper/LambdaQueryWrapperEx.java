package jasmine.framework.persistence.mybatisplus.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jasmine.framework.persistence.mybatisplus.dynamic.DataAuthUtil;

/**
 * @author mh.z
 */
public class LambdaQueryWrapperEx<T> extends AbstractLambdaQueryWrapperEx<T, LambdaQueryWrapperEx<T>> {

    public static <T> LambdaQueryWrapper<T> wrapper() {
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
