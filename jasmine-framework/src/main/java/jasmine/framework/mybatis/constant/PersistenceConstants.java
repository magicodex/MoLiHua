package jasmine.framework.mybatis.constant;

/**
 * @author mh.z
 */
public interface PersistenceConstants {

    /** 最大同批插入数量 */
    int BATCH_INSERT_SIZE = 1000;
    /** 最大同批更新数量 */
    int BATCH_UPDATE_SIZE = 1000;
    /** 最大同批删除数量 */
    int BATCH_DELETE_SIZE = 1000;
    /** 最大同批查询数量 */
    int BATCH_SELECT_SIZE = 1000;
}
