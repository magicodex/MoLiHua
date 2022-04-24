package jasmine.framework.persistence.mybatisplus.util;

import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;
import jasmine.core.util.QI18nUtil;
import jasmine.framework.persistence.entity.BaseEntity;
import jasmine.framework.persistence.entity.BaseI18nEntity;

import java.time.ZonedDateTime;
import java.util.Collection;

/**
 * @author mh.z
 */
public class BaseEntityHelper {

    /**
     * 填充字段
     *
     * @param entity
     */
    public static void fillInsert(BaseEntity entity) {
        QCheckUtil.notNull(entity, "entity null");
        Long userId = CurrentSubject.getUserId();
        ZonedDateTime currentTime = ZonedDateTime.now();

        // 创建人ID
        entity.setCreatedBy(userId);
        // 创建日期
        entity.setCreatedDate(currentTime);
        // 最后更新人ID
        entity.setLastUpdatedBy(userId);
        // 最后更新日期
        entity.setLastUpdatedDate(currentTime);

        if (entity instanceof BaseI18nEntity) {
            BaseI18nEntity baseI18nEntity = (BaseI18nEntity) entity;
            // 语言代码
            baseI18nEntity.setCreatedLang(QI18nUtil.getLanguage());
        }
    }

    /**
     * 填充字段
     *
     * @param entities
     */
    public static void fillInsert(Collection<? extends BaseEntity> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (QCollUtil.isEmpty(entities)) {
            return;
        }

        Long userId = CurrentSubject.getUserId();
        ZonedDateTime currentTime = ZonedDateTime.now();

        if (QCollUtil.getFirst(entities) instanceof BaseI18nEntity) {
            String langCode = QI18nUtil.getLanguage();

            entities.forEach((entity) -> {
                // 创建人ID
                entity.setCreatedBy(userId);
                // 创建日期
                entity.setCreatedDate(currentTime);
                // 最后更新人ID
                entity.setLastUpdatedBy(userId);
                // 最后更新日期
                entity.setLastUpdatedDate(currentTime);
                // 语言代码
                BaseI18nEntity baseI18nEntity = (BaseI18nEntity) entity;
                baseI18nEntity.setCreatedLang(langCode);
            });
        } else {
            entities.forEach((entity) -> {
                // 创建人ID
                entity.setCreatedBy(userId);
                // 创建日期
                entity.setCreatedDate(currentTime);
                // 最后更新人ID
                entity.setLastUpdatedBy(userId);
                // 最后更新日期
                entity.setLastUpdatedDate(currentTime);
            });
        }
    }

    /**
     * 填充字段
     *
     * @param entity
     */
    public static void fillUpdate(BaseEntity entity) {
        QCheckUtil.notNull(entity, "entity null");

        // 最后更新人ID
        entity.setLastUpdatedBy(CurrentSubject.getUserId());
        // 最后更新日期
        entity.setLastUpdatedDate(ZonedDateTime.now());
    }

    /**
     * 填充字段
     *
     * @param entities
     */
    public static void fillUpdate(Collection<? extends BaseEntity> entities) {
        QCheckUtil.notNull(entities, "entities null");

        if (QCollUtil.isEmpty(entities)) {
            return;
        }

        Long userId = CurrentSubject.getUserId();
        ZonedDateTime currentTime = ZonedDateTime.now();

        entities.forEach((entity) -> {
            // 最后更新人ID
            entity.setLastUpdatedBy(userId);
            // 最后更新日期
            entity.setLastUpdatedDate(currentTime);
        });
    }

}
