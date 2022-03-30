package jasmine.framework.persistence.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author mh.z
 */
public class BaseI18nDaoSupport<M extends BaseMapper<T>, T> extends BaseDaoSupport<M, T>
        implements BaseI18nDAO<T> {

}
