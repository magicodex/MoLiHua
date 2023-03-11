package jasmine.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import jasmine.core.context.CurrentSubject;
import jasmine.core.util.I18nUtil;
import org.apache.ibatis.reflection.MetaObject;

import java.time.ZonedDateTime;

/**
 * <p>
 * 填充字段。
 * </p>
 *
 * @author mh.z
 */
public class BaseEntityMetaObjectHandler implements MetaObjectHandler {
    private static final String FIELD_CREATED_BY = "createdBy";
    private static final String FIELD_CREATED_DATE = "createdDate";
    private static final String FIELD_LAST_UPDATED_BY = "lastUpdatedBy";
    private static final String FIELD_LAST_UPDATED_DATE = "lastUpdatedDate";
    private static final String FIELD_VERSION_NUMBER = "versionNumber";
    private static final String FIELD_CREATED_LANG = "createdLang";

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = CurrentSubject.getUserId();

        if (userId != null) {
            // 创建人ID
            strictInsertFill(metaObject, FIELD_CREATED_BY, Long.class, userId);
            // 最后更新人ID
            strictInsertFill(metaObject, FIELD_LAST_UPDATED_BY, Long.class, userId);
        }

        ZonedDateTime now = ZonedDateTime.now();
        // 创建日期
        strictInsertFill(metaObject, FIELD_CREATED_DATE, ZonedDateTime.class, now);
        // 最后更新日期
        strictInsertFill(metaObject, FIELD_LAST_UPDATED_DATE, ZonedDateTime.class, now);
        // 版本号
        strictInsertFill(metaObject, FIELD_VERSION_NUMBER, Integer.class, 1);

        // 语言代码
        if (metaObject.hasSetter(FIELD_CREATED_LANG)) {
            strictInsertFill(metaObject, FIELD_CREATED_LANG, String.class, I18nUtil.getLanguage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = CurrentSubject.getUserId();

        if (userId != null) {
            // 最后更新人ID
            strictInsertFill(metaObject, FIELD_LAST_UPDATED_BY, Long.class, userId);
        }

        ZonedDateTime now = ZonedDateTime.now();
        // 最后更新日期
        strictUpdateFill(metaObject, FIELD_LAST_UPDATED_DATE, ZonedDateTime.class, now);
    }

}
