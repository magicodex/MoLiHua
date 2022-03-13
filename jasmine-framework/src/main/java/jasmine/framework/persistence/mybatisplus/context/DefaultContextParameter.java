package jasmine.framework.persistence.mybatisplus.context;

import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QI18nUtil;

/**
 * @author mh.z
 */
public class DefaultContextParameter implements ContextParameter {

    @Override
    public Long getTenantId() {
        return CurrentSubject.getTenantId();
    }

    @Override
    public String getLandCode() {
        return QI18nUtil.getLanguage();
    }

}
