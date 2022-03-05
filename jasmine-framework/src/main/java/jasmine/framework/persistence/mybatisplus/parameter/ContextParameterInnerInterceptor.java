package jasmine.framework.persistence.mybatisplus.parameter;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;

import java.sql.Connection;

/**
 * <p>
 * 可以在 Mapper XML里通过 #{_current.*} 获取上下文信息，
 * 比如通过 #{_current.tenantId} 获取租户ID。
 * </p>
 *
 * @author mh.z
 */
public class ContextParameterInnerInterceptor implements InnerInterceptor {
    /** 上下文参数 */
    private ContextParameter contextParameter;

    public ContextParameterInnerInterceptor(ContextParameter contextParameter) {
        this.contextParameter = contextParameter;
    }

    @Override
    public void beforePrepare(StatementHandler statementHandler, Connection connection,
                              Integer transactionTimeout) {
        setContextParameter(statementHandler);
    }

    @Override
    public void beforeGetBoundSql(StatementHandler statementHandler) {
        setContextParameter(statementHandler);
    }

    /**
     * 设置上下文参数
     *
     * @param statementHandler
     */
    protected void setContextParameter(StatementHandler statementHandler) {
        BoundSql boundSql = statementHandler.getBoundSql();
        boundSql.setAdditionalParameter("_current", contextParameter);
    }

}
