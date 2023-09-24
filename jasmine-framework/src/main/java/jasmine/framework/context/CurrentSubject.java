package jasmine.framework.context;

import jasmine.framework.common.util.CheckUtil;

/**
 * <p>
 * 获取当前用户的有关信息。
 * </p>
 *
 * @author mh.z
 */
public class CurrentSubject implements WithContext {
    /** 用户信息提供者 */
    private static SubjectProvider subjectProvider;
    /** 用户信息提供者 NULL 时的错误信息 */
    private static final String SUBJECT_PROVIDER_NULL_MESSAGE;

    public static final Long DEFAULT_USER_ID = -1L;
    public static final Long DEFAULT_TENANT_ID = -1L;

    static {
        SUBJECT_PROVIDER_NULL_MESSAGE = CurrentSubject.class.getSimpleName() + ".subjectProvider null";
    }

    public static void initUtil(SubjectProvider subjectProvider) {
        CurrentSubject.subjectProvider = subjectProvider;
    }

    public static boolean isInitialized() {
        return subjectProvider != null;
    }

    public static SubjectProvider getSubjectProvider() {
        return subjectProvider;
    }

    /**
     * 返回用户ID
     *
     * @return
     */
    public static Long getUserId() {
        CheckUtil.notNullProp(subjectProvider, SUBJECT_PROVIDER_NULL_MESSAGE);

        return subjectProvider.getCurrentUserId();
    }

    /**
     * 返回租户ID
     *
     * @return
     */
    public static Long getTenantId() {
        CheckUtil.notNullProp(subjectProvider, SUBJECT_PROVIDER_NULL_MESSAGE);

        return subjectProvider.getCurrentTenantId();
    }

    /**
     * 返回用户
     *
     * @return
     */
    public static Object getSubject() {
        CheckUtil.notNullProp(subjectProvider, SUBJECT_PROVIDER_NULL_MESSAGE);

        return subjectProvider.getCurrentSubject();
    }

    /**
     * 设置用户
     *
     * @param tenantId
     * @param userId
     */
    public static void setSubject(Long tenantId, Long userId) {
        CheckUtil.notNullProp(subjectProvider, SUBJECT_PROVIDER_NULL_MESSAGE);

        subjectProvider.setCurrentSubject(tenantId, userId);
    }

}
