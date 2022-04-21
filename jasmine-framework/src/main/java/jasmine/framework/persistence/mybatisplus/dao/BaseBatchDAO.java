package jasmine.framework.persistence.mybatisplus.dao;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public interface BaseBatchDAO<T> {

    /**
     * 保存记录
     *
     * @param entities
     */
    void saveBatch(@Nonnull Collection<T> entities);

    /**
     * 更新记录
     *
     * @param entities
     */
    void updateBatchById(@Nonnull Collection<T> entities);

    /**
     * 删除记录
     *
     * @param ids
     * @return
     */
    int deleteByIds(@Nonnull Collection<? extends Serializable> ids);

    /**
     * 删除记录
     *
     * @param ids
     */
    void strictDeleteByIds(@Nonnull Collection<? extends Serializable> ids);

    /**
     * 查询记录
     *
     * @param ids
     * @return
     */
    List<T> listByIds(@Nonnull Collection<? extends Serializable> ids);
}
