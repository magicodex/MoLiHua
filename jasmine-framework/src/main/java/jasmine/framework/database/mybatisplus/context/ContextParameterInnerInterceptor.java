package jasmine.framework.database.mybatisplus.context;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;

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
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds,
                            ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        setContextParameter(boundSql);
    }

    @Override
    public void beforeUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        //
    }

    @Override
    public void beforePrepare(StatementHandler statementHandler, Connection connection, Integer transactionTimeout) {
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
        setContextParameter(boundSql);
    }

    /**
     * 设置上下文参数
     *
     * @param boundSql
     */
    protected void setContextParameter(BoundSql boundSql) {
        boundSql.setAdditionalParameter("_current", contextParameter);
    }

}
