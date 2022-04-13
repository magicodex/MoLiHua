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

    /** 语句参数 */
    String STATEMENT_PARAM_ID = "id";
    String STATEMENT_PARAM_IDS = "ids";
    String STATEMENT_PARAM_COLUMNS = "columns";
    String STATEMENT_PARAM_VALUES = "values";
    String STATEMENT_PARAM_LANG_CODE = "langCode";
    String STATEMENT_PARAM_CREATED_DATE = "createdDate";
    String STATEMENT_PARAM_CREATED_BY = "createdBy";
    String STATEMENT_PARAM_LAST_UPDATED_DATE = "lastUpdatedDate";
    String STATEMENT_PARAM_LAST_UPDATED_BY = "lastUpdatedBy";
    String STATEMENT_PARAM_VERSION_NUMBER = "versionNumber";
}
