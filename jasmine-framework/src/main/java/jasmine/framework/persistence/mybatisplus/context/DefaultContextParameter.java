package jasmine.framework.persistence.mybatisplus.context;

import jasmine.core.context.CurrentSubject;

/**
 * @author mh.z
 */
public class DefaultContextParameter implements ContextParameter {

    @Override
    public Long getTenantId() {
        return CurrentSubject.getTenantId();
    }

}
