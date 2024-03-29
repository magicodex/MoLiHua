package jasmine.framework.database.mybatisplus.dynamic;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.context.WithContext;

/**
 * @author mh.z
 */
public class DataAuthUtil implements WithContext {
    private static DataAuthProvider provider;

    public static void initUtil(DataAuthProvider provider) {
        DataAuthUtil.provider = provider;
    }

    /**
     * 生成SQL
     *
     * @param tableName
     * @param aliasName
     * @return
     */
    public static String generateSql(String tableName, String aliasName) {
        CheckUtil.notNullProp(provider, "provider null");

        return provider.generateSql(tableName, aliasName);
    }

    /**
     * 生成SQL
     *
     * @param entityType
     * @param aliasName
     * @return
     */
    public static String generateSql(Class<?> entityType, String aliasName) {
        CheckUtil.notNullProp(provider, "provider null");

        return provider.generateSql(entityType, aliasName);
    }

}
