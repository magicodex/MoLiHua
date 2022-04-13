package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.core.util.QCollectionUtil;
import jasmine.framework.persistence.entity.BaseI18nEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public class FakeI18nEntityFacade implements I18nEntityFacade {

    @Override
    public int insertI18n(Collection<? extends BaseI18nEntity> entities) {
        return entities.size();
    }

    @Override
    public int updateI18n(Collection<? extends BaseI18nEntity> entities) {
        return entities.size();
    }

    @Override
    public int deleteI18n(Collection<? extends Serializable> ids) {
        return ids.size();
    }

    @Override
    public <T extends BaseI18nEntity> List<T> populateI18n(Collection<T> entities) {
        return QCollectionUtil.castToList(entities);
    }

}
