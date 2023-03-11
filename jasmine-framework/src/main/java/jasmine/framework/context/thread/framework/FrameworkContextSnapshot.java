package jasmine.framework.context.thread.framework;

import jasmine.framework.context.thread.ContextSnapshot;
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
