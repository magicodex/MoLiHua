package jasmine.framework.persistence.constant;

/**
 * @author mh.z
 */
public interface MapperConstants {

    /** 最大同批插入数量 */
    int BATCH_INSERT_SIZE = 1000;
    /** 最大同批更新数量 */
    int BATCH_UPDATE_SIZE = 1000;
    /** 最大同批删除数量 */
    int BATCH_DELETE_SIZE = 1000;

    /** SQL参数名 */
    String SQL_PARAM_ID = "id";
    String SQL_PARAM_IDS = "ids";
    String SQL_PARAM_COLUMNS = "columns";
    String SQL_PARAM_VALUES = "values";
    String SQL_PARAM_LANG_CODE = "langCode";
    String SQL_PARAM_CREATED_DATE = "createdDate";
    String SQL_PARAM_CREATED_BY = "createdBy";
    String SQL_PARAM_LAST_UPDATED_DATE = "lastUpdatedDate";
    String SQL_PARAM_LAST_UPDATED_BY = "lastUpdatedBy";
    String SQL_PARAM_VERSION_NUMBER = "versionNumber";

    /** SQL列名 */
    String SQL_COLUMN_ID = "id";
    String SQL_COLUMN_VERSION_NUMBER = "version_number";
}
