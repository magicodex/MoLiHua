package jasmine.core.context;

import jasmine.core.util.QCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mh.z
 */
@Component
public class CurrentSubject {
    private static SubjectProvider provider;

    public CurrentSubject(@Autowired(required = false) SubjectProvider subjectProvider) {
        CurrentSubject.provider = subjectProvider;
    }

    /**
     * 返回用户ID
     *
     * @return
     */
    public static Long getUserId() {
        QCheckUtil.notNullProp(provider, "provider null");

        return provider.getCurrentUserId();
    }

    /**
     * 返回租户ID
     *
     * @return
     */
    public static Long getTenantId() {
        QCheckUtil.notNullProp(provider, "provider null");

        return provider.getCurrentTenantId();
    }

    /**
     * 返回用户
     *
     * @return
     */
    public static Object getSubject() {
        return provider.getSubject();
    }

}
