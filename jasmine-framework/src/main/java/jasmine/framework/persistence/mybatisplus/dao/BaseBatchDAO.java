package jasmine.framework.persistence.mybatisplus.dao;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 批量CRUD接口。
 * </p>
 *
 * @author mh.z
 */
public interface BaseBatchDAO<T> {

    /**
     * 批量保存记录
     *
     * @param entities
     */
    void saveBatch(@Nonnull Collection<T> entities);

    /**
     * 批量更新记录
     *
     * @param entities
     */
    void updateBatchById(@Nonnull Collection<T> entities);

    /**
     * 批量删除记录
     *
     * @param ids
     * @return
     */
    int deleteByIds(@Nonnull Collection<? extends Serializable> ids);

    /**
     * 批量删除记录并检查是否删除成功
     *
     * @param ids
     */
    void strictDeleteByIds(@Nonnull Collection<? extends Serializable> ids);

    /**
     * 批量查询记录
     *
     * @param ids
     * @return
     */
    List<T> listByIds(@Nonnull Collection<? extends Serializable> ids);
}
