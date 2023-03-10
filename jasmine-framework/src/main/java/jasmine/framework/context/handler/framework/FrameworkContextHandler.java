package jasmine.framework.context.handler.framework;

import jasmine.core.util.MapperUtil;
import jasmine.framework.context.handler.ContextHandler;
import jasmine.framework.context.handler.ContextSnapshot;
import jasmine.framework.persistence.datasource.context.DataSourceContext;
import jasmine.framework.persistence.datasource.context.DataSourceContextHolder;

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
