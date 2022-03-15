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
    public <T extends BaseI18nEntity> int insertI18n(Collection<T> entities) {
        return 0;
    }

    @Override
    public <T extends BaseI18nEntity> List<T> updateI18nThenFillEntity(Collection<T> entities) {
        return QCollectionUtil.castToList(entities);
    }

    @Override
    public <T extends BaseI18nEntity> List<T> populateI18n(Collection<T> entities) {
        return QCollectionUtil.castToList(entities);
    }

    @Override
    public int deleteI18n(Collection<? extends Serializable> ids) {
        return 0;
    }

}
