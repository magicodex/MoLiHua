package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.framework.persistence.entity.BaseI18nEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public class Default18nEntityFacade implements I18nEntityFacade {

    @Override
    public int insertI18n(Collection<? extends BaseI18nEntity> entities) {
        // TODO
        return 0;
    }

    @Override
    public int updateI18n(Collection<? extends BaseI18nEntity> entities) {
        // TODO
        return 0;
    }

    @Override
    public int deleteI18n(Collection<? extends Serializable> ids) {
        // TODO
        return 0;
    }

    @Override
    public <T> List<T> populateI18n(Collection<T> collection, Class<? extends BaseI18nEntity> entityType) {
        // TODO
        return null;
    }

}
