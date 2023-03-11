package jasmine.framework.context.impl.thread;

import jasmine.core.util.MapperUtil;
import jasmine.framework.context.thread.ContextHandler;
import jasmine.framework.context.thread.ContextSnapshot;
import jasmine.framework.database.datasource.DataSourceContext;
import jasmine.framework.database.datasource.DataSourceContextHolder;

/**
 * @author mh.z
 */
public class FrameworkContextHandler implements ContextHandler {

    @Override
    public ContextSnapshot copy() {
        DataSourceContext dataSourceContext = DataSourceContextHolder.getContext();

        if (dataSourceContext != null) {
            // 复制出新的对象
            dataSourceContext = MapperUtil.mapTo(dataSourceContext, DataSourceContext.class);
        }

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
