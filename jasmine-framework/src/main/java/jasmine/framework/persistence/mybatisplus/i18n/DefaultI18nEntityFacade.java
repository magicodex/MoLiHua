package jasmine.framework.persistence.mybatisplus.i18n;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;
import jasmine.core.util.QI18nUtil;
import jasmine.core.util.number.LongValue;
import jasmine.framework.persistence.constant.MapperConstants;
import jasmine.framework.persistence.entity.BaseI18nEntity;
import jasmine.framework.persistence.mybatisplus.i18n.support.I18nCRUD;
import jasmine.framework.persistence.mybatisplus.i18n.support.I18nMeta;
import jasmine.framework.persistence.mybatisplus.i18n.support.I18nRecord;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mh.z
 */
public class DefaultI18nEntityFacade implements I18nEntityFacade {
    private static final Log mybatisLog = LogFactory.getLog(DefaultI18nEntityFacade.class);
    private SqlSessionTemplate sqlSessionTemplate;
    private Map<Class<?>, I18nMeta> i18nMetaOfEntities;

    private static final int BATCH_INSERT_SIZE = MapperConstants.BATCH_INSERT_SIZE;
    private static final int BATCH_UPDATE_SIZE = MapperConstants.BATCH_UPDATE_SIZE;

    public DefaultI18nEntityFacade(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.i18nMetaOfEntities = new ConcurrentHashMap<>();
    }

    @Override
    public int insertI18n(Collection<? extends BaseI18nEntity> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (QCollUtil.isEmpty(entities)) {
            return 0;
        }

        Class<?> entityType = getEntityType(entities);
        I18nMeta i18nMeta = getI18nMeta(entityType);
        String i18nTable = getI18nTable(entityType);

        // 新增多语言记录
        SqlHelper.executeBatch(entityType, mybatisLog, entities, BATCH_INSERT_SIZE, (sqlSession, entity) -> {
            Map<String, String> i18nDataMap = i18nMeta.getI18nData(entity);
            I18nCRUD.insert(sqlSession, i18nTable, entity.getId(),
                    entity.getLangCode(), i18nDataMap);
        });

        return entities.size();
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
        String i18nTable = getI18nTable(entityType);
        String langCode = QI18nUtil.getLanguage();

        // 查询多语言记录
        List<Long> idList = QCollUtil.mapToList(entities, BaseI18nEntity::getId);
        List<I18nRecord> recordList = I18nCRUD.select(sqlSessionTemplate, i18nTable, idList, langCode);
        Map<Long, I18nRecord> i18nRecordMap = QCollUtil.toMap(recordList, I18nRecord::getId);

        SqlHelper.executeBatch(entityType, mybatisLog, entities, BATCH_UPDATE_SIZE, (sqlSession, entity) -> {
            Map<String, String> i18nDataMap = i18nMeta.getI18nData(entity);
            Long recordId = entity.getId();
            I18nRecord i18nRecord = i18nRecordMap.get(recordId);

            if (i18nRecord != null) {
                // 更新多语言记录
                rowCount.add(I18nCRUD.update(sqlSession, i18nTable, recordId,
                        langCode, i18nDataMap, i18nRecord.getVersionNumber()));
            } else {
                // 新增多语言记录
                rowCount.add(I18nCRUD.insert(sqlSession, i18nTable, recordId,
                        langCode, i18nDataMap));
            }
        });

        return (int) rowCount.get();
    }

    @Override
    public int deleteI18n(Class<? extends BaseI18nEntity> entityType, Collection<? extends Serializable> ids) {
        QCheckUtil.notNull(entityType, "entityType null");
        QCheckUtil.notNull(ids, "ids null");

        if (QCollUtil.isEmpty(ids)) {
            return 0;
        }

        String i18nTable = getI18nTable(entityType);
        // 删除多语言记录
        int rowCount = I18nCRUD.delete(sqlSessionTemplate, i18nTable, ids, null);

        return rowCount;
    }

    @Override
    public <T extends BaseI18nEntity> List<T> populateI18n(Collection<T> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (QCollUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        Class<?> entityType = getEntityType(entities);
        String i18nTable = getI18nTable(entityType);
        String langCode = QI18nUtil.getLanguage();

        // 查询多语言记录
        List<Long> idList = QCollUtil.mapToList(entities, BaseI18nEntity::getId);
        List<I18nRecord> i18nRecordList = I18nCRUD.select(sqlSessionTemplate, i18nTable, idList, langCode);

        // 填充多语言字段
        if (QCollUtil.isNotEmpty(i18nRecordList)) {
            Map<Long, I18nRecord> i18nRecordMap = QCollUtil.toMap(i18nRecordList, I18nRecord::getId);
            I18nMeta i18nMeta = getI18nMeta(entityType);

            entities.forEach((entity) -> {
                Long id = entity.getId();
                I18nRecord record = i18nRecordMap.get(id);

                i18nMeta.populateI18nField(entity, record);
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
     * 返回多语言表
     *
     * @param entityType
     * @return
     */
    protected String getI18nTable(Class<?> entityType) {
        QCheckUtil.notNull(entityType, "entityType null");

        TableName annotation = entityType.getAnnotation(TableName.class);
        if (annotation == null) {
            return null;
        }

        String tableName = annotation.value();
        if (tableName == null) {
            return null;
        }

        return (tableName + MapperConstants.I18N_TABLE_NAME_SUFFIX);
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
            return I18nMeta.createI18nMeta(mappingKey);
        });

        return i18nMeta;
    }

}
