package jasmine.framework.database.mybatisplus.i18n;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.CollectionUtil;
import jasmine.framework.common.util.I18nUtil;
import jasmine.framework.common.util.ObjectUtil;
import jasmine.framework.database.mybatisplus.entity.BaseI18nEntity;
import jasmine.framework.database.mybatisplus.constant.MybatisConstants;
import jasmine.framework.database.mybatisplus.i18n.support.I18nMeta;
import jasmine.framework.database.mybatisplus.i18n.support.I18nRecord;
import jasmine.framework.database.mybatisplus.i18n.support.PopulateFunction;
import jasmine.framework.database.mybatisplus.i18n.support.I18nCrud;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * @author mh.z
 */
public class DefaultI18nEntityFacade implements I18nEntityFacade {
    private static final Log mybatisLog = LogFactory.getLog(DefaultI18nEntityFacade.class);
    private SqlSessionTemplate sqlSessionTemplate;
    private Map<Class<?>, I18nMeta> i18nMetaOfEntities;

    /** 多语言表名称后缀 */
    private static final String I18N_TABLE_NAME_SUFFIX = "_i18n";

    public DefaultI18nEntityFacade(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.i18nMetaOfEntities = new ConcurrentHashMap<>();
    }

    @Override
    public int insertI18n(Collection<? extends BaseI18nEntity> entities) {
        CheckUtil.notNull(entities, "entities null");

        if (CollectionUtil.isEmpty(entities)) {
            return 0;
        }

        Class<?> entityType = getEntityType(entities);
        I18nMeta i18nMeta = getI18nMeta(entityType);
        String i18nTable = getI18nTable(entityType);
        String langCode = I18nUtil.getLanguage();

        // 新增多语言记录
        SqlHelper.executeBatch(entityType, mybatisLog, entities, MybatisConstants.BATCH_INSERT_SIZE,
                (sqlSession, entity) -> {
                    Map<String, String> i18nDataMap = i18nMeta.getI18nData(entity);
                    I18nCrud i18nCRUD = new I18nCrud(sqlSession, i18nTable);
                    Long entityId = CheckUtil.notNull(entity.getId());

                    i18nCRUD.insert(entityId, langCode, i18nDataMap, true);
                });

        return entities.size();
    }

    @Override
    public int updateI18n(Collection<? extends BaseI18nEntity> entities) {
        CheckUtil.notNull(entities, "entities null");

        if (CollectionUtil.isEmpty(entities)) {
            return 0;
        }

        Class<?> entityType = getEntityType(entities);
        I18nMeta i18nMeta = getI18nMeta(entityType);
        String i18nTable = getI18nTable(entityType);
        String langCode = I18nUtil.getLanguage();

        List<Long> idList = CollectionUtil.mapToList(entities, BaseI18nEntity::getId);
        I18nCrud i18nCRUD = new I18nCrud(sqlSessionTemplate, i18nTable);
        // 查询多语言记录
        List<I18nRecord> i18nRecordList = i18nCRUD.select(idList, langCode);
        Map<Long, I18nRecord> i18nRecordMap = CollectionUtil.toMap(i18nRecordList, I18nRecord::getId);

        entities.forEach((entity) -> {
            Map<String, String> i18nDataMap = i18nMeta.getI18nData(entity);
            Long entityId = entity.getId();
            I18nRecord i18nRecord = i18nRecordMap.get(entityId);

            // 更新多语言记录
            if (i18nRecord != null) {
                i18nCRUD.update(entityId, langCode, i18nDataMap, null, true);
            } else {
                String createdLang = entity.getCreatedLang();
                int rowCount = i18nCRUD.update(entityId, createdLang, i18nDataMap, null, false);

                if (rowCount <= 0) {
                    i18nCRUD.insert(entityId, createdLang, i18nDataMap, true);
                }
            }
        });

        return entities.size();
    }

    @Override
    public int updateI18nThenFillEntities(Collection<? extends BaseI18nEntity> entities) {
        CheckUtil.notNull(entities, "entities null");

        // 更新多语言记录
        int result = updateI18n(entities);

        String langCode = I18nUtil.getLanguage();
        // 过滤出需要填充的记录
        List<? extends BaseI18nEntity> targetList = CollectionUtil.chooseToList(entities, (entity) -> {
            return ObjectUtil.notEqual(entity.getCreatedLang(), langCode);
        });

        if (CollectionUtil.isNotEmpty(targetList)) {
            // 填充默认多语言数据
            populateDefaultI18n(targetList);
        }

        return result;
    }

    @Override
    public int deleteI18n(Class<? extends BaseI18nEntity> entityType,
                          Collection<? extends Serializable> ids) {
        CheckUtil.notNull(entityType, "entityType null");
        CheckUtil.notNull(ids, "ids null");

        if (CollectionUtil.isEmpty(ids)) {
            return 0;
        }

        String i18nTable = getI18nTable(entityType);
        I18nCrud i18nCRUD = new I18nCrud(sqlSessionTemplate, i18nTable);
        // 删除多语言记录
        int rowCount = i18nCRUD.delete(ids, null);

        return rowCount;
    }

    @Override
    public <T extends BaseI18nEntity> List<T> populateI18n(Collection<T> entities) {
        CheckUtil.notNull(entities, "entities null");

        if (CollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        Class<?> entityType = getEntityType(entities);
        String i18nTable = getI18nTable(entityType);
        String langCode = I18nUtil.getLanguage();

        List<Long> idList = CollectionUtil.mapToList(entities, BaseI18nEntity::getId);
        I18nCrud i18nCRUD = new I18nCrud(sqlSessionTemplate, i18nTable);
        // 查询多语言记录
        List<I18nRecord> i18nRecordList = i18nCRUD.select(idList, langCode);

        // 填充多语言字段
        if (CollectionUtil.isNotEmpty(i18nRecordList)) {
            Map<Long, I18nRecord> i18nRecordMap = CollectionUtil.toMap(i18nRecordList, I18nRecord::getId);
            I18nMeta i18nMeta = getI18nMeta(entityType);

            entities.forEach((entity) -> {
                Long id = entity.getId();
                I18nRecord record = i18nRecordMap.get(id);

                i18nMeta.populateI18nData(entity, record);
            });
        }

        return CollectionUtil.toList(entities);
    }

    @Override
    public <T, E extends BaseI18nEntity> List<T> populateI18n(Collection<T> targets, Class<E> entityType,
                                                              Function<T, Serializable> keyMapper,
                                                              PopulateFunction<T, E> populateFunction) {
        CheckUtil.notNull(targets, "targets null");
        CheckUtil.notNull(entityType, "entityType null");
        CheckUtil.notNull(keyMapper, "keyMapper null");
        CheckUtil.notNull(populateFunction, "populateFunction null");

        if (CollectionUtil.isEmpty(targets)) {
            return Collections.emptyList();
        }

        String i18nTable = getI18nTable(entityType);
        String langCode = I18nUtil.getLanguage();

        List<Serializable> idList = CollectionUtil.mapToList(targets, keyMapper);
        I18nCrud i18nCRUD = new I18nCrud(sqlSessionTemplate, i18nTable);
        // 查询多语言记录
        List<I18nRecord> i18nRecordList = i18nCRUD.select(idList, langCode);

        // 填充多语言字段
        if (CollectionUtil.isNotEmpty(i18nRecordList)) {
            Map<Long, I18nRecord> i18nRecordMap = CollectionUtil.toMap(i18nRecordList, I18nRecord::getId);
            I18nMeta i18nMeta = getI18nMeta(entityType);

            targets.forEach((target) -> {
                Serializable id = keyMapper.apply(target);
                I18nRecord record = i18nRecordMap.get(id);

                if (record != null) {
                    E entity = ReflectUtil.newInstance(entityType);
                    i18nMeta.populateI18nData(entity, record);
                    // 填充多语言
                    populateFunction.populate(target, entity);
                }
            });
        }

        return CollectionUtil.toList(targets);
    }

    @Override
    public <T extends BaseI18nEntity> List<T> populateDefaultI18n(Collection<T> entities) {
        CheckUtil.notNull(entities, "entities null");

        if (CollectionUtil.isEmpty(entities)) {
            return Collections.emptyList();
        }

        Class<?> entityType = getEntityType(entities);
        String i18nTable = getI18nTable(entityType);

        List<Long> idList = CollectionUtil.mapToList(entities, BaseI18nEntity::getId);
        I18nCrud i18nCRUD = new I18nCrud(sqlSessionTemplate, i18nTable);
        // 查询多语言记录
        List<I18nRecord> i18nRecordList = i18nCRUD.selectDefault(idList);

        // 填充多语言字段
        if (CollectionUtil.isNotEmpty(i18nRecordList)) {
            Map<Long, I18nRecord> i18nRecordMap = CollectionUtil.toMap(i18nRecordList, I18nRecord::getId);
            I18nMeta i18nMeta = getI18nMeta(entityType);

            entities.forEach((entity) -> {
                Long id = entity.getId();
                I18nRecord record = i18nRecordMap.get(id);

                i18nMeta.populateI18nData(entity, record);
            });
        }

        return CollectionUtil.toList(entities);
    }

    /**
     * 返回实体类型
     *
     * @param entities
     * @return
     */
    protected Class<?> getEntityType(Collection<?> entities) {
        CheckUtil.notNull(entities, "entities null");

        if (CollectionUtil.isEmpty(entities)) {
            return null;
        }

        Object entity = CollectionUtil.getFirst(entities);
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
        CheckUtil.notNull(entityType, "entityType null");

        I18nMeta i18nMeta = i18nMetaOfEntities.computeIfAbsent(entityType, (mappingKey) -> {
            return I18nMeta.createI18nMeta(mappingKey);
        });

        return i18nMeta;
    }

    /**
     * 返回多语言表
     *
     * @param entityType
     * @return
     */
    protected String getI18nTable(Class<?> entityType) {
        CheckUtil.notNull(entityType, "entityType null");

        TableName annotation = entityType.getAnnotation(TableName.class);
        if (annotation == null) {
            return null;
        }

        String tableName = annotation.value();
        if (tableName == null) {
            return null;
        }

        return (tableName + I18N_TABLE_NAME_SUFFIX);
    }

}
