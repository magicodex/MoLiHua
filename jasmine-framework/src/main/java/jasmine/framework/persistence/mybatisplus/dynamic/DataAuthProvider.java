package jasmine.framework.persistence.mybatisplus.dynamic;

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
}
