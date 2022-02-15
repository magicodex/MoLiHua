package jasmine.framework.persistence.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import jasmine.core.context.CurrentSubject;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

/**
 * @author mh.z
 */
@Component
public class BaseEntityMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        ZonedDateTime now = ZonedDateTime.now();
        Long userId = CurrentSubject.getUserId();

        strictInsertFill(metaObject, "createdDate", ZonedDateTime.class, now);
        strictInsertFill(metaObject, "createdBy", Long.class, userId);
        strictInsertFill(metaObject, "lastUpdatedDate", ZonedDateTime.class, now);
        strictInsertFill(metaObject, "lastUpdatedBy", Long.class, userId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        ZonedDateTime now = ZonedDateTime.now();
        Long userId = CurrentSubject.getUserId();

        strictUpdateFill(metaObject, "lastUpdatedDate", ZonedDateTime.class, now);
        strictInsertFill(metaObject, "lastUpdatedBy", Long.class, userId);
    }

}
