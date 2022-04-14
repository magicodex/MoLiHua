package jasmine.framework.persistence.mybatisplus.i18n;

import jasmine.core.context.CurrentSubject;
import jasmine.core.exception.ApplicationException;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;
import jasmine.core.util.batch.BatchCallUtil;
import jasmine.core.util.number.LongValue;
import jasmine.framework.common.constant.CommonMessages;
import jasmine.framework.persistence.constant.MapperConstants;
import jasmine.framework.persistence.mybatisplus.i18n.support.I18nRecord;
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
public class DefaultI18nRecordFacade implements I18nRecordFacade {
    private static final String STATEMENT_INSERT = "jasmine.EntityI18n.insertI18n";
    private static final String STATEMENT_UPDATE = "jasmine.EntityI18n.updateI18n";
    private static final String STATEMENT_DELETE = "jasmine.EntityI18n.deleteI18n";
    private static final String STATEMENT_SELECT = "jasmine.EntityI18n.selectI18n";
    private static final int BATCH_DELETE_SIZE = MapperConstants.BATCH_DELETE_SIZE;

    @Override
    public int insert(SqlSession session, String table, Long id,
                      String language, Map<String, String> data) {
        QCheckUtil.notNull(session, "session null");
        QCheckUtil.notNull(data, "data null");

        ZonedDateTime currentTime = ZonedDateTime.now();
        Long userId = CurrentSubject.getUserId();

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(MapperConstants.SQL_PARAM_TABLE, table);
        paramMap.put(MapperConstants.SQL_PARAM_ID, id);
        paramMap.put(MapperConstants.SQL_PARAM_LANG_CODE, language);
        paramMap.put(MapperConstants.SQL_PARAM_CREATED_DATE, currentTime);
        paramMap.put(MapperConstants.SQL_PARAM_CREATED_BY, userId);
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_DATE, currentTime);
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_BY, userId);
        paramMap.put(MapperConstants.SQL_PARAM_VERSION_NUMBER, 1);
        paramMap.put(MapperConstants.SQL_PARAM_COLUMNS, data.keySet());
        paramMap.put(MapperConstants.SQL_PARAM_VALUES, data.values());

        // 插入多语言记录
        int rowCount = session.insert(STATEMENT_INSERT, paramMap);
        return rowCount;
    }

    @Override
    public int update(SqlSession session, String table, Long id,
                      String language, Map<String, String> data, Integer version) {
        QCheckUtil.notNull(session, "session null");
        QCheckUtil.notNull(data, "data null");

        ZonedDateTime currentTime = ZonedDateTime.now();
        Long userId = CurrentSubject.getUserId();

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(MapperConstants.SQL_PARAM_TABLE, table);
        paramMap.put(MapperConstants.SQL_PARAM_LANG_CODE, language);
        paramMap.put(MapperConstants.SQL_PARAM_ID, id);
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_DATE, currentTime);
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_BY, userId);
        paramMap.put(MapperConstants.SQL_PARAM_VERSION_NUMBER, version);
        paramMap.put(MapperConstants.SQL_PARAM_VALUES, data);

        // 更新多语言记录
        int rowCount = session.update(STATEMENT_UPDATE, paramMap);
        if (rowCount < 1) {
            throw new ApplicationException(CommonMessages.UPDATE_ROW_COUNT_MISMATCH);
        }

        return rowCount;
    }

    @Override
    public int delete(SqlSession session, String table, Collection<? extends Serializable> ids,
                      String language) {
        QCheckUtil.notNull(session, "session null");
        QCheckUtil.notNull(ids, "ids null");

        if (QCollUtil.isEmpty(ids)) {
            return 0;
        }

        LongValue rowCount = new LongValue(0);
        // 删除多语言记录
        BatchCallUtil.call(ids, BATCH_DELETE_SIZE, (part) -> {
            Object parameter = Map.of(MapperConstants.SQL_PARAM_TABLE, table,
                    MapperConstants.SQL_PARAM_IDS, part,
                    MapperConstants.SQL_PARAM_LANG_CODE, language);

            rowCount.add(session.delete(STATEMENT_DELETE, parameter));
        });

        return (int) rowCount.get();
    }

    @Override
    public List<I18nRecord> select(SqlSession session, String table, Collection<? extends Serializable> ids,
                                   String language) {
        QCheckUtil.notNull(session, "session null");
        QCheckUtil.notNull(ids, "ids null");

        if (QCollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }

        // 组装查询参数
        Object parameter = Map.of(MapperConstants.SQL_PARAM_TABLE, table,
                MapperConstants.SQL_PARAM_IDS, ids,
                MapperConstants.SQL_PARAM_LANG_CODE, language);

        // 查询多语言记录
        List<Map> recordList = session.selectList(STATEMENT_SELECT, parameter);
        List<I18nRecord> i18nRecordList = QCollUtil.mapToList(recordList, (record) -> {
            return new I18nRecord(record);
        });

        return i18nRecordList;
    }

}
