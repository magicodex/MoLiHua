package jasmine.framework.database.mybatisplus.dynamic;

/**
 * @author mh.z
 */
public interface DataAuthProvider {

    /**
     * 生成SQL
     *
     * @param tableName
     * @param aliasName
     * @return
     */
    String generateSql(String tableName, String aliasName);

    /**
     * 生成SQL
     *
     * @param entityType
     * @param aliasName
     * @return
     */
    String generateSql(Class<?> entityType, String aliasName);
}
