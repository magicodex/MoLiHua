package jasmine.framework.persistence.mybatisplus.dao;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * @author mh.z
 */
public interface BaseDAO<T> {

    /**
     * 保存记录
     *
     * @param entity
     */
    void save(@Nonnull T entity);

    /**
     * 更新记录
     *
     * @param entity
     */
    void updateById(@Nonnull T entity);

    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    boolean deleteById(@Nonnull Serializable id);

    /**
     * 删除记录
     *
     * @param id
     */
    void strictDeleteById(@Nonnull Serializable id);

    /**
     * 查询记录
     *
     * @param id
     * @return
     */
    T getById(@Nonnull Serializable id);
}
