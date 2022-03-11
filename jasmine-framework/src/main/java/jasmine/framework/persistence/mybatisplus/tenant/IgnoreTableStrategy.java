package jasmine.framework.persistence.mybatisplus.tenant;

/**
 * <p>
 * 忽略租户的表的策略。
 * </p>
 *
 * @author mh.z
 */
public interface IgnoreTableStrategy {

    /**
     * 添加忽略的表
     *
     * @param tableName
     */
    void addIgnoreTable(String tableName);
}
