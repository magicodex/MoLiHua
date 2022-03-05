package jasmine.framework.persistence.mybatisplus.tenant;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import jasmine.core.context.CurrentSubject;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

import java.util.Collection;

/**
 * @author mh.z
 */
public class DefaultTenantLineHandler implements TenantLineHandler {
    private Collection<String> tableNamesWithoutTenant;

    public DefaultTenantLineHandler(Collection<String> tableNamesWithoutTenant) {
        this.tableNamesWithoutTenant = tableNamesWithoutTenant;
    }

    @Override
    public Expression getTenantId() {
        Long tenantId = CurrentSubject.getTenantId();

        return new LongValue(tenantId);
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return tableNamesWithoutTenant.contains(tableName);
    }

}
