package jasmine.framework.database.integration;

import jasmine.framework.context.thread.ContextSnapshot;
import jasmine.framework.database.datasource.DataSourceContext;
import jasmine.framework.database.datasource.DataSourceContextHolder;

/**
 * @author mh.z
 */
public class DatabaseContextSnapshot implements ContextSnapshot {
    private DataSourceContext dataSourceContext;

    public DatabaseContextSnapshot(DataSourceContext dataSourceContext) {
        this.dataSourceContext = dataSourceContext;
    }

    @Override
    public void copyToCurrentThread() {
        DataSourceContextHolder.setContext(dataSourceContext);
    }

}
