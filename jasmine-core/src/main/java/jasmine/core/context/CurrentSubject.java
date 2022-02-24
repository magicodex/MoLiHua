package jasmine.core.context;

import jasmine.core.util.QCheckUtil;

/**
 * <p>
 * 获取当前用户的有关信息。
 * </p>
 *
 * @author mh.z
 */
public class CurrentSubject {
    /** 用户信息提供者 */
    private static SubjectProvider subjectProvider;
    /** 用户信息提供者 NULL 时的错误信息 */
    private static final String SUBJECT_PROVIDER_NULL_MESSAGE;

    static {
        SUBJECT_PROVIDER_NULL_MESSAGE = CurrentSubject.class.getSimpleName() + ".subjectProvider null";
    }

    public static void initUtil(SubjectProvider subjectProvider) {
        CurrentSubject.subjectProvider = subjectProvider;
    }

    /**
     * 返回用户ID
     *
     * @return
     */
    public static Long getUserId() {
        QCheckUtil.notNullProp(subjectProvider, SUBJECT_PROVIDER_NULL_MESSAGE);

        return subjectProvider.getCurrentUserId();
    }

    /**
     * 返回租户ID
     *
     * @return
     */
    public static Long getTenantId() {
        QCheckUtil.notNullProp(subjectProvider, SUBJECT_PROVIDER_NULL_MESSAGE);

        return subjectProvider.getCurrentTenantId();
    }

    /**
     * 返回用户
     *
     * @return
     */
    public static Object getSubject() {
        QCheckUtil.notNullProp(subjectProvider, SUBJECT_PROVIDER_NULL_MESSAGE);

        return subjectProvider.getCurrentSubject();
    }

    /**
     * 设置用户
     *
     * @param tenantId
     * @param userId
     */
    public static void setSubject(Long tenantId, Long userId) {
        QCheckUtil.notNullProp(subjectProvider, SUBJECT_PROVIDER_NULL_MESSAGE);

        subjectProvider.setCurrentSubject(tenantId, userId);
    }

}
