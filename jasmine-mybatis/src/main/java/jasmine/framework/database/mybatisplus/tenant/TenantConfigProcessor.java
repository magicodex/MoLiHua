package jasmine.framework.database.mybatisplus.tenant;

/**
 * <p>
 * 租户配置处理器。
 * </p>
 *
 * @author mh.z
 */
public interface TenantConfigProcessor {

    /**
     * 忽略多租户条件的表
     *
     * @param strategy
     */
    void ignoreTable(IgnoreTableStrategy strategy);
}
