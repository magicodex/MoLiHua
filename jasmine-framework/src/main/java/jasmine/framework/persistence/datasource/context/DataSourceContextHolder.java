package jasmine.framework.persistence.datasource.context;

/**
 * @author mh.z
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<DataSourceContext> CONTEXT_LOCAL = new ThreadLocal<>();

    /**
     * 返回上下文
     *
     * @return
     */
    public static DataSourceContext getContext() {
        DataSourceContext context = CONTEXT_LOCAL.get();

        if (context == null) {
            context = new DefaultDataSourceContext();
            CONTEXT_LOCAL.set(context);
        }

        return context;
    }

    /**
     * 设置上下文
     *
     * @param context
     */
    public static void setContext(DataSourceContext context) {
        CONTEXT_LOCAL.set(context);
    }

    /**
     * 清空上下文
     *
     */
    public static void clearContext() {
        CONTEXT_LOCAL.remove();
    }

}
