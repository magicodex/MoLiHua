package jasmine.core.context;

/**
 * <p>
 * 提供获取用户有关信息的接口。
 * </p>
 *
 * @author mh.z
 */
public interface SubjectProvider {

    /**
     * 返回当前的用户ID
     *
     * @return
     */
    Long getCurrentUserId();

    /**
     * 返回当前的租户ID
     *
     * @return
     */
    Long getCurrentTenantId();

    /**
     * 返回当前的用户
     *
     * @return
     */
    Object getCurrentSubject();
}
