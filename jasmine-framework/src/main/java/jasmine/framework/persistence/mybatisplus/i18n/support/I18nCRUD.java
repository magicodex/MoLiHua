package jasmine.framework.persistence.mybatisplus.i18n.support;

import jasmine.core.context.CurrentSubject;
import jasmine.core.exception.ApplicationException;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;
import jasmine.core.util.QNewUtil;
import jasmine.core.util.batch.BatchCallUtil;
import jasmine.framework.common.constant.CommonMessages;
import jasmine.framework.persistence.constant.PersistenceConstants;
import org.apache.ibatis.session.SqlSession;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 多语言CRUD。
 * </p>
 *
 * @author mh.z
 */
public class I18nCRUD {
    private SqlSession sqlSession;
    private String tableName;

    private static final String STATEMENT_INSERT = "jasmine.EntityI18n.insertI18n";
    private static final String STATEMENT_UPDATE = "jasmine.EntityI18n.updateI18n";
    private static final String STATEMENT_DELETE = "jasmine.EntityI18n.deleteI18n";
    private static final String STATEMENT_SELECT = "jasmine.EntityI18n.selectI18n";

    private static final String PARAM_TABLE = "table";
    private static final String PARAM_COLUMNS = "columns";
    private static final String PARAM_VALUES = "values";
    private static final String PARAM_ID = "id";
    private static final String PARAM_IDS = "ids";
    private static final String PARAM_LANG_CODE = "langCode";
    private static final String PARAM_DEFAULT_FLAG = "defaultFlag";
    private static final String PARAM_CREATED_DATE = "createdDate";
    private static final String PARAM_CREATED_BY = "createdBy";
    private static final String PARAM_LAST_UPDATED_DATE = "lastUpdatedDate";
    private static final String PARAM_LAST_UPDATED_BY = "lastUpdatedBy";
    private static final String PARAM_VERSION_NUMBER = "versionNumber";

    public I18nCRUD(SqlSession sqlSession, String tableName) {
        this.sqlSession = sqlSession;
        this.tableName = tableName;
    }

    /**
     * 新增多语言
     *
     * @param id
     * @param langCode
     * @param data
     * @param defaultFlag
     * @return
     */
    public int insert(@Nonnull Long id, @Nonnull String langCode,
                      @Nonnull Map<String, String> data, boolean defaultFlag) {
        QCheckUtil.notNull(id, "id null");
        QCheckUtil.notNull(langCode, "langCode null");
        QCheckUtil.notNull(data, "data null");

        ZonedDateTime currentTime = ZonedDateTime.now();
        Long userId = CurrentSubject.getUserId();

        Map<String, Object> paramMap = new HashMap<>();
        // 多语言表
        paramMap.put(PARAM_TABLE, tableName);
        // 记录主键
        paramMap.put(PARAM_ID, id);
        paramMap.put(PARAM_LANG_CODE, langCode);
        // 默认标志
        paramMap.put(PARAM_DEFAULT_FLAG, defaultFlag);
        // 审计字段
        paramMap.put(PARAM_CREATED_DATE, currentTime);
        paramMap.put(PARAM_CREATED_BY, userId);
        paramMap.put(PARAM_LAST_UPDATED_DATE, currentTime);
        paramMap.put(PARAM_LAST_UPDATED_BY, userId);
        // 版本字段
        paramMap.put(PARAM_VERSION_NUMBER, 1);
        // 多语言列
        paramMap.put(PARAM_COLUMNS, data.keySet());
        paramMap.put(PARAM_VALUES, data.values());

        // 新增多语言记录
        sqlSession.insert(STATEMENT_INSERT, paramMap);

        return 1;
    }

    /**
     * 更新多语言
     *
     * @param id
     * @param langCode
     * @param data
     * @param versionNumber
     * @return
     */
    public int update(@Nonnull Serializable id, @Nonnull String langCode,
                      @Nonnull Map<String, String> data, @Nonnull Integer versionNumber) {
        QCheckUtil.notNull(id, "id null");
        QCheckUtil.notNull(langCode, "langCode null");
        QCheckUtil.notNull(data, "data null");
        QCheckUtil.notNull(versionNumber, "versionNumber null");

        ZonedDateTime currentTime = ZonedDateTime.now();
        Long userId = CurrentSubject.getUserId();

        Map<String, Object> paramMap = QNewUtil.map();
        // 多语言表
        paramMap.put(PARAM_TABLE, tableName);
        // 记录主键
        paramMap.put(PARAM_ID, id);
        paramMap.put(PARAM_LANG_CODE, langCode);
        // 审计字段
        paramMap.put(PARAM_LAST_UPDATED_DATE, currentTime);
        paramMap.put(PARAM_LAST_UPDATED_BY, userId);
        // 版本字段
        paramMap.put(PARAM_VERSION_NUMBER, versionNumber);
        // 多语言列
        paramMap.put(PARAM_VALUES, data);

        // 更新多语言记录
        int rowCount = sqlSession.update(STATEMENT_UPDATE, paramMap);
        if (rowCount != 1) {
            throw new ApplicationException(CommonMessages.UPDATE_ROW_COUNT_MISMATCH);
        }

        return 1;
    }

    /**
     * 删除多语言
     *
     * @param ids
     * @param langCode
     * @return
     */
    public int delete(@Nonnull Collection<? extends Serializable> ids,
                      @Nullable String langCode) {
        QCheckUtil.notNull(ids, "ids null");

        if (QCollUtil.isEmpty(ids)) {
            return 0;
        }

        Set<? extends Serializable> idSet = new HashSet<>(ids);
        // 删除多语言记录
        BatchCallUtil.call(idSet, PersistenceConstants.BATCH_DELETE_SIZE, (partialIds) -> {
            Map<String, Object> paramMap = QNewUtil.map();
            paramMap.put(PARAM_TABLE, tableName);
            paramMap.put(PARAM_IDS, partialIds);
            paramMap.put(PARAM_LANG_CODE, langCode);

            sqlSession.delete(STATEMENT_DELETE, paramMap);
        });

        return idSet.size();
    }

    /**
     * 查询多语言
     *
     * @param ids
     * @param langCode
     * @return
     */
    public List<I18nRecord> select(@Nonnull Collection<? extends Serializable> ids,
                                   @Nullable String langCode) {
        QCheckUtil.notNull(ids, "ids null");

        if (QCollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }

        Map<String, Object> paramMap = QNewUtil.map();
        paramMap.put(PARAM_TABLE, tableName);
        paramMap.put(PARAM_IDS, ids);
        paramMap.put(PARAM_LANG_CODE, langCode);

        // 查询多语言记录
        List<Map> recordList = sqlSession.selectList(STATEMENT_SELECT, paramMap);
        if (QCollUtil.isEmpty(recordList)) {
            return Collections.emptyList();
        }

        List<I18nRecord> i18nRecordList = QCollUtil.mapToList(recordList, (record) -> {
            return new I18nRecord(record);
        });

        return i18nRecordList;
    }

    /**
     * 查询多语言
     *
     * @param ids
     * @return
     */
    public List<I18nRecord> selectDefault(@Nonnull Collection<? extends Serializable> ids) {
        QCheckUtil.notNull(ids, "ids null");

        if (QCollUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }

        Map<String, Object> paramMap = QNewUtil.map();
        paramMap.put(PARAM_TABLE, tableName);
        paramMap.put(PARAM_IDS, ids);
        paramMap.put(PARAM_DEFAULT_FLAG, true);

        // 查询多语言记录
        List<Map> recordList = sqlSession.selectList(STATEMENT_SELECT, paramMap);
        if (QCollUtil.isEmpty(recordList)) {
            return Collections.emptyList();
        }

        List<I18nRecord> i18nRecordList = QCollUtil.mapToList(recordList, (record) -> {
            return new I18nRecord(record);
        });

        return i18nRecordList;
    }

}