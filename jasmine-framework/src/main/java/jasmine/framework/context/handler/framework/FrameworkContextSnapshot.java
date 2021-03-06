package jasmine.framework.context.handler.framework;

import jasmine.framework.context.handler.ContextSnapshot;
import jasmine.framework.persistence.datasource.context.DataSourceContext;
import jasmine.framework.persistence.datasource.context.DataSourceContextHolder;

/**
 * @author mh.z
 */
public class FrameworkContextSnapshot implements ContextSnapshot {
    private DataSourceContext dataSourceContext;

    public FrameworkContextSnapshot(DataSourceContext dataSourceContext) {
        this.dataSourceContext = dataSourceContext;
    }

    @Override
    public void copyToCurrentThread() {
        DataSourceContextHolder.setContext(dataSourceContext);
    }

}
