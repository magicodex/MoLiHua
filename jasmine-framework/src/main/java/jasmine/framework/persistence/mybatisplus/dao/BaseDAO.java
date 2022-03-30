package jasmine.framework.persistence.mybatisplus.dao;

import java.io.Serializable;

/**
 * @author mh.z
 */
public interface BaseDAO<T> {

    /**
     * 保存记录
     *
     * @param entity
     * @return
     */
    boolean save(T entity);

    /**
     * 查询记录
     *
     * @param id
     * @return
     */
    T getById(Serializable id);

    /**
     * 更新记录
     *
     * @param entity
     * @return
     */
    boolean updateById(T entity);

    /**
     * 删除记录
     *
     * @param id
     * @return
     */
    boolean deleteById(Serializable id);
}
