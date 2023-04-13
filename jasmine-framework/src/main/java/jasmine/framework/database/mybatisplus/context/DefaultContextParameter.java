package jasmine.framework.database.mybatisplus.context;

import jasmine.framework.context.CurrentSubject;
import jasmine.framework.common.util.I18nUtil;

/**
 * @author mh.z
 */
public class DefaultContextParameter implements ContextParameter {

    @Override
    public Long getTenantId() {
        return CurrentSubject.getTenantId();
    }

    @Override
    public String getLangCode() {
        return I18nUtil.getLanguage();
    }

}
