package jasmine.framework.persistence.mybatisplus.tenant;

/**
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
