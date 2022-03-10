package jasmine.framework.persistence.mybatisplus.tenant;

/**
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
