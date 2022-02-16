package jasmine.core.context;

/**
 * @author mh.z
 */
public interface SubjectProvider {

    /**
     * 返回用户ID
     *
     * @return
     */
    Long getCurrentUserId();

    /**
     * 返回租户ID
     *
     * @return
     */
    Long getCurrentTenantId();

    /**
     * 返回用户
     *
     * @return
     */
    Object getSubject();
}
