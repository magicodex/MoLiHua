package jasmine.framework.persistence.mybatisplus.context;

import jasmine.core.context.SubjectProvider;

/**
 * @author mh.z
 */
public class DefaultContextParameter implements ContextParameter {
    private SubjectProvider subjectProvider;

    public DefaultContextParameter(SubjectProvider subjectProvider) {
        this.subjectProvider = subjectProvider;
    }

    @Override
    public Long getTenantId() {
        if (subjectProvider == null) {
            return null;
        }

        return subjectProvider.getCurrentTenantId();
    }

}
