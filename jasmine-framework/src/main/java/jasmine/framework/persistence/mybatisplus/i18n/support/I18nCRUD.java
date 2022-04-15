package jasmine.framework.persistence.mybatisplus.i18n.support;

import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;
import jasmine.core.util.QNewUtil;
import jasmine.core.util.batch.BatchCallUtil;
import jasmine.core.util.number.LongValue;
import jasmine.framework.persistence.constant.MapperConstants;
import org.apache.ibatis.session.SqlSession;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mh.z
 */
public class I18nCRUD {

    private static final String STATEMENT_INSERT = "jasmine.EntityI18n.insertI18n";
    private static final String STATEMENT_UPDATE = "jasmine.EntityI18n.updateI18n";
    private static final String STATEMENT_DELETE = "jasmine.EntityI18n.deleteI18n";
    private static final String STATEMENT_SELECT = "jasmine.EntityI18n.selectI18n";

    /**
     * 新增多语言
     *
     * @param sqlSession
     * @param tableName
     * @param id
     * @param langCode
     * @param data
     * @return
     */
    public static int insert(SqlSession sqlSession, String tableName, Long id,
                             String langCode, Map<String, String> data) {
        QCheckUtil.notNull(sqlSession, "sqlSession null");
        QCheckUtil.notNull(data, "data null");

        ZonedDateTime currentTime = ZonedDateTime.now();
        Long userId = CurrentSubject.getUserId();

        Map<String, Object> paramMap = new HashMap<>();
        // 多语言表
        paramMap.put(MapperConstants.SQL_PARAM_TABLE, tableName);
        // 记录主键
        paramMap.put(MapperConstants.SQL_PARAM_ID, id);
        paramMap.put(MapperConstants.SQL_PARAM_LANG_CODE, langCode);
        // 审计字段
        paramMap.put(MapperConstants.SQL_PARAM_CREATED_DATE, currentTime);
        paramMap.put(MapperConstants.SQL_PARAM_CREATED_BY, userId);
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_DATE, currentTime);
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_BY, userId);
        // 乐观锁版本
        paramMap.put(MapperConstants.SQL_PARAM_VERSION_NUMBER, 1);
        // 多语言列
        paramMap.put(MapperConstants.SQL_PARAM_COLUMNS, data.keySet());
        paramMap.put(MapperConstants.SQL_PARAM_VALUES, data.values());

        // 新增多语言记录
        sqlSession.insert(STATEMENT_INSERT, paramMap);

        return 1;
    }

    /**
     * 更新多语言
     *
     * @param sqlSession
     * @param tableName
     * @param id
     * @param langCode
     * @param data
     * @param versionNumber
     * @return
     */
    public static int update(SqlSession sqlSession, String tableName, Long id,
                             String langCode, Map<String, String> data, Integer versionNumber) {
        QCheckUtil.notNull(sqlSession, "sqlSession null");
        QCheckUtil.notNull(data, "data null");

        ZonedDateTime currentTime = ZonedDateTime.now();
        Long userId = CurrentSubject.getUserId();

        Map<String, Object> paramMap = new HashMap<>();
        // 多语言表
        paramMap.put(MapperConstants.SQL_PARAM_TABLE, tableName);
        // 记录主键
        paramMap.put(MapperConstants.SQL_PARAM_ID, id);
        paramMap.put(MapperConstants.SQL_PARAM_LANG_CODE, langCode);
        // 审计字段
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_DATE, currentTime);
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_BY, userId);
        // 多语言列
        paramMap.put(MapperConstants.SQL_PARAM_VALUES, data);

        // 更新多语言记录
        int rowCount = sqlSession.update(STATEMENT_UPDATE, paramMap);

        return rowCount;
    }

    /**
     * 删除多语言
     *
     * @param sqlSession
     * @param tableName
     * @param ids
     * @param langCode
     * @return
     */
    public static int delete(SqlSession sqlSession, String tableName,
                             Collection<? extends Serializable> ids, String langCode) {
        QCheckUtil.notNull(sqlSession, "sqlSession null");
        QCheckUtil.notNull(ids, "ids null");

        if (QCollUtil.isEmpty(ids)) {
            return 0;
        }

        LongValue rowCount = new LongValue(0);
        // 删除多语言记录
        BatchCallUtil.call(ids, MapperConstants.BATCH_DELETE_SIZE, (partIds) -> {
            Map<String, Object> paramMap = QNewUtil.map();
            paramMap.put(MapperConstants.SQL_PARAM_TABLE, tableName);
            paramMap.put(MapperConstants.SQL_PARAM_IDS, partIds);
            paramMap.put(MapperConstants.SQL_PARAM_LANG_CODE, langCode);

            rowCount.add(sqlSession.delete(STATEMENT_DELETE, paramMap));
        });

        return (int) rowCount.get();
    }

    /**
     * 查询多语言
     *
     * @param sqlSession
     * @param tableName
     * @param ids
     * @param langCode
     * @return
     */
    public static List<I18nRecord> select(SqlSession sqlSession, String tableName,
                                          Collection<? extends Serializable> ids, String langCode) {
        QCheckUtil.notNull(sqlSession, "sqlSession null");
        QCheckUtil.notNull(ids, "ids null");

        if (QCollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }

        // 查询多语言记录
        Object parameter = Map.of(MapperConstants.SQL_PARAM_TABLE, tableName,
                MapperConstants.SQL_PARAM_IDS, ids,
                MapperConstants.SQL_PARAM_LANG_CODE, langCode);
        List<Map> recordList = sqlSession.selectList(STATEMENT_SELECT, parameter);

        if (QCollUtil.isEmpty(recordList)) {
            return Collections.emptyList();
        }

        List<I18nRecord> i18nRecordList = QCollUtil.mapToList(recordList, (record) -> {
            return new I18nRecord(record);
        });

        return i18nRecordList;
    }

}
