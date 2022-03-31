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
     * @return
     */
    boolean saveBatch(@Nonnull Collection<T> entities);

    /**
     * 更新记录
     *
     * @param entities
     * @return
     */
    boolean updateBatchById(@Nonnull Collection<T> entities);

    /**
     * 删除记录
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(@Nonnull Collection<Serializable> ids);

    /**
     * 查询记录
     *
     * @param ids
     * @return
     */
    List<T> listByIds(@Nonnull Collection<Serializable> ids);
}
