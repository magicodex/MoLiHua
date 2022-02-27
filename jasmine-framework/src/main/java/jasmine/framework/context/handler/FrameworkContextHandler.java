package jasmine.framework.context.handler;

import jasmine.core.context.handler.ContextHandler;
import jasmine.core.context.handler.ContextSnapshot;
import jasmine.framework.persistence.datasource.context.DataSourceContext;
import jasmine.framework.persistence.datasource.context.DataSourceContextHolder;

/**
 * @author mh.z
 */
public class FrameworkContextHandler implements ContextHandler {

    @Override
    public ContextSnapshot copy() {
        DataSourceContext dataSourceContext = DataSourceContextHolder.getContext();
        ContextSnapshot snapshot = new FrameworkContextSnapshot(dataSourceContext);

        return snapshot;
    }

    @Override
    public void init() {
        DataSourceContextHolder.clearContext();
    }

    @Override
    public void clear() {
        DataSourceContextHolder.clearContext();
    }

}
