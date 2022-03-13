package jasmine.framework.persistence.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import jasmine.core.context.CurrentSubject;
import jasmine.core.util.QI18nUtil;
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

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = CurrentSubject.getUserId();

        if (userId != null) {
            // 创建人ID
            strictInsertFill(metaObject, "createdBy", Long.class, userId);
            // 最后更新人ID
            strictInsertFill(metaObject, "lastUpdatedBy", Long.class, userId);
        }

        ZonedDateTime now = ZonedDateTime.now();
        // 创建日期
        strictInsertFill(metaObject, "createdDate", ZonedDateTime.class, now);
        // 最后更新日期
        strictInsertFill(metaObject, "lastUpdatedDate", ZonedDateTime.class, now);

        // 版本号
        strictInsertFill(metaObject, "versionNumber", Integer.class, 1);

        // 语言代码
        strictInsertFill(metaObject, "langCode", String.class, QI18nUtil.getLanguage());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = CurrentSubject.getUserId();

        if (userId != null) {
            // 最后更新人ID
            strictInsertFill(metaObject, "lastUpdatedBy", Long.class, userId);
        }

        ZonedDateTime now = ZonedDateTime.now();
        // 最后更新日期
        strictUpdateFill(metaObject, "lastUpdatedDate", ZonedDateTime.class, now);
    }

}
