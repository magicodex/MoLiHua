package jasmine.framework.context.impl.thread;

import jasmine.framework.context.thread.ContextSnapshot;
import jasmine.framework.database.datasource.DataSourceContext;
import jasmine.framework.database.datasource.DataSourceContextHolder;

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
