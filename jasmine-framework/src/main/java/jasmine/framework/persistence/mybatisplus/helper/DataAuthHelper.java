package jasmine.framework.persistence.mybatisplus.helper;

import jasmine.core.util.QCheckUtil;

/**
 * @author mh.z
 */
public class DataAuthHelper {
    private static DataAuthProvider provider;

    public static void initUtil(DataAuthProvider provider) {
        DataAuthHelper.provider = provider;
    }

    public static String generateSql(String tableName, String aliasName) {
        QCheckUtil.notNullProp(provider, "provider null");

        return provider.generateSql(tableName, aliasName);
    }

}
