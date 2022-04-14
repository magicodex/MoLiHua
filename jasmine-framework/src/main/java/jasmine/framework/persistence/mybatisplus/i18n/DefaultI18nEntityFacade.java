package jasmine.framework.persistence.mybatisplus.i18n;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jasmine.core.context.CurrentSubject;
import jasmine.core.exception.ApplicationException;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;
import jasmine.core.util.QI18nUtil;
import jasmine.core.util.batch.BatchCallUtil;
import jasmine.core.util.number.LongValue;
import jasmine.framework.common.constant.CommonMessages;
import jasmine.framework.persistence.constant.MapperConstants;
import jasmine.framework.persistence.entity.BaseI18nEntity;
import jasmine.framework.persistence.mybatisplus.i18n.support.I18nMeta;
import jasmine.framework.persistence.mybatisplus.i18n.support.I18nRecord;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mh.z
 */
public class DefaultI18nEntityFacade implements I18nEntityFacade {
    private static final Log mybatisLog = LogFactory.getLog(DefaultI18nEntityFacade.class);
    private SqlSession sqlSession;
    private Map<Class<?>, I18nMeta> i18nMetaOfEntities;

    private static final String STATEMENT_INSERT = "jasmine.EntityI18n.insertI18n";
    private static final String STATEMENT_UPDATE = "jasmine.EntityI18n.updateI18n";
    private static final String STATEMENT_DELETE = "jasmine.EntityI18n.deleteI18n";
    private static final String STATEMENT_SELECT = "jasmine.EntityI18n.selectI18n";
    private static final int BATCH_INSERT_SIZE = MapperConstants.BATCH_INSERT_SIZE;
    private static final int BATCH_UPDATE_SIZE = MapperConstants.BATCH_UPDATE_SIZE;
    private static final int BATCH_DELETE_SIZE = MapperConstants.BATCH_DELETE_SIZE;

    public DefaultI18nEntityFacade(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.i18nMetaOfEntities = new ConcurrentHashMap<>();
    }

    @Override
    public int insertI18n(Collection<? extends BaseI18nEntity> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (QCollUtil.isEmpty(entities)) {
            return 0;
        }

        LongValue rowCount = new LongValue(0);
        Class<?> entityType = getEntityType(entities);
        I18nMeta i18nMeta = getI18nMeta(entityType);

        // 新增多语言记录
        SqlHelper.executeBatch(entityType, mybatisLog, entities, BATCH_INSERT_SIZE, (sqlSession, entity) -> {
            rowCount.add(doInsertI18n(i18nMeta, entity));
        });

        return (int) rowCount.get();
    }

    /**
     * 新增多语言记录
     *
     * @param i18nMeta
     * @param entity
     * @return
     */
    protected int doInsertI18n(I18nMeta i18nMeta, BaseI18nEntity entity) {
        QCheckUtil.notNull(i18nMeta, "i18nMeta null");
        QCheckUtil.notNull(entity, "entity null");
        ZonedDateTime currentTime = ZonedDateTime.now();
        Long userId = CurrentSubject.getUserId();
        Map<String, String> i18nDataMap = i18nMeta.getI18nData(entity);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(MapperConstants.SQL_PARAM_ID, entity.getId());
        paramMap.put(MapperConstants.SQL_PARAM_LANG_CODE, entity.getLangCode());
        paramMap.put(MapperConstants.SQL_PARAM_CREATED_DATE, currentTime);
        paramMap.put(MapperConstants.SQL_PARAM_CREATED_BY, userId);
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_DATE, currentTime);
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_BY, userId);
        paramMap.put(MapperConstants.SQL_PARAM_VERSION_NUMBER, 1);
        paramMap.put(MapperConstants.SQL_PARAM_COLUMNS, i18nDataMap.keySet());
        paramMap.put(MapperConstants.SQL_PARAM_VALUES, i18nDataMap.values());

        // 插入多语言记录
        int rowCount = sqlSession.insert(STATEMENT_INSERT, paramMap);
        return rowCount;
    }

    @Override
    public int updateI18n(Collection<? extends BaseI18nEntity> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (QCollUtil.isEmpty(entities)) {
            return 0;
        }

        LongValue rowCount = new LongValue(0);
        Class<?> entityType = getEntityType(entities);
        I18nMeta i18nMeta = getI18nMeta(entityType);
        String langCode = QI18nUtil.getLanguage();

        // 组装查询参数
        List<Long> idList = QCollUtil.mapToList(entities, BaseI18nEntity::getId);
        Object parameter = Map.of(MapperConstants.SQL_PARAM_IDS, idList,
                MapperConstants.SQL_PARAM_LANG_CODE, langCode);

        // 查询多语言记录
        List<Map> recordList = sqlSession.selectList(STATEMENT_SELECT, parameter);
        Map<Long, I18nRecord> recordMap = I18nRecord.toI18nRecordMap(recordList);

        SqlHelper.executeBatch(entityType, mybatisLog, entities, BATCH_UPDATE_SIZE, (sqlSession, entity) -> {
            Long id = entity.getId();
            I18nRecord record = recordMap.get(id);

            if (record != null) {
                // 更新多语言记录
                rowCount.add(doUpdateI18n(i18nMeta, entity, record.getVersionNumber()));
            } else {
                // 插入多语言记录
                rowCount.add(doInsertI18n(i18nMeta, entity));
            }
        });

        return (int) rowCount.get();
    }

    /**
     * 更新多语言记录
     *
     * @param i18nMeta
     * @param entity
     * @param versionNumber
     * @return
     */
    protected int doUpdateI18n(I18nMeta i18nMeta, BaseI18nEntity entity, Integer versionNumber) {
        QCheckUtil.notNull(i18nMeta, "i18nMeta null");
        QCheckUtil.notNull(entity, "entity null");
        QCheckUtil.notNull(versionNumber, "versionNumber null");
        ZonedDateTime currentTime = ZonedDateTime.now();
        Long userId = CurrentSubject.getUserId();
        Map<String, String> i18nDataMap = i18nMeta.getI18nData(entity);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(MapperConstants.SQL_PARAM_ID, entity.getId());
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_DATE, currentTime);
        paramMap.put(MapperConstants.SQL_PARAM_LAST_UPDATED_BY, userId);
        paramMap.put(MapperConstants.SQL_PARAM_VERSION_NUMBER, versionNumber);
        paramMap.put(MapperConstants.SQL_PARAM_VALUES, i18nDataMap.entrySet());

        // 更新多语言记录
        int rowCount = sqlSession.update(STATEMENT_UPDATE, paramMap);
        if (rowCount < 1) {
            throw new ApplicationException(CommonMessages.UPDATE_ROW_COUNT_MISMATCH);
        }

        return rowCount;
    }


    @Override
    public int deleteI18n(Collection<? extends Serializable> ids) {
        QCheckUtil.notNull(ids, "ids null");

        if (QCollUtil.isEmpty(ids)) {
            return 0;
        }

        LongValue rowCount = new LongValue(0);
        // 删除多语言记录
        BatchCallUtil.call(ids, BATCH_DELETE_SIZE, (part) -> {
            Object parameter = Map.of(MapperConstants.SQL_PARAM_IDS, part);
            rowCount.add(sqlSession.delete(STATEMENT_DELETE, parameter));
        });

        return (int) rowCount.get();
    }

    @Override
    public <T extends BaseI18nEntity> List<T> populateI18n(Collection<T> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (QCollUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        // 组装查询参数
        String langCode = QI18nUtil.getLanguage();
        List<Long> idList = QCollUtil.mapToList(entities, BaseI18nEntity::getId);
        Object parameter = Map.of(MapperConstants.SQL_PARAM_IDS, idList,
                MapperConstants.SQL_PARAM_LANG_CODE, langCode);

        // 查询多语言记录
        List<Map> recordList = sqlSession.selectList(STATEMENT_SELECT, parameter);
        Map<Long, I18nRecord> recordMap = I18nRecord.toI18nRecordMap(recordList);

        // 填充多语言的值
        if (QCollUtil.isNotEmpty(recordList)) {
            Class<?> entityType = getEntityType(entities);
            I18nMeta i18nMeta = getI18nMeta(entityType);

            entities.forEach((entity) -> {
                Long id = entity.getId();
                I18nRecord record = recordMap.get(id);

                i18nMeta.populateI18n(entity, record);
            });
        }

        return QCollUtil.castToList(entities);
    }

    /**
     * 返回实体类型
     *
     * @param entities
     * @return
     */
    protected Class<?> getEntityType(Collection<?> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (QCollUtil.isEmpty(entities)) {
            return null;
        }

        Object entity = QCollUtil.getFirst(entities);
        Class<?> entityType = entity.getClass();

        return entityType;
    }

    /**
     * 返回多语言信息
     *
     * @param entityType
     * @return
     */
    protected I18nMeta getI18nMeta(Class<?> entityType) {
        QCheckUtil.notNull(entityType, "entityType null");

        I18nMeta i18nMeta = i18nMetaOfEntities.computeIfAbsent(entityType, (mappingKey) -> {
            return I18nMeta.create(mappingKey);
        });

        return i18nMeta;
    }

}
