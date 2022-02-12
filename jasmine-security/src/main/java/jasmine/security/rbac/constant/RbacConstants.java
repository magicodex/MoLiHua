package jasmine.security.rbac.constant;

/**
 * @author mh.z
 */
public interface RbacConstants {

    /** 资源访问方式-GET */
    String RESOURCE_ACCESS_METHOD_GET = "GET";

    /** 资源访问方式-POST */
    String RESOURCE_ACCESS_METHOD_POST = "POST";

    /** 资源访问方式-PUT */
    String RESOURCE_ACCESS_METHOD_PUT = "PUT";

    /** 资源访问方式-任意 */
    String RESOURCE_ACCESS_METHOD_ANY = "*";

    /** 资源访问策略-公开，所有人都能访问 */
    String RESOURCE_ACCESS_POLICY_ANONYMOUS = "ANONYMOUS";

    /** 资源访问策略-已登录，已登录的就可访问 */
    String RESOURCE_ACCESS_POLICY_AUTHENTICATED = "AUTHENTICATED";

    /** 资源访问策略-已授权，已授权的才能访问 */
    String RESOURCE_ACCESS_POLICY_GRANTED = "GRANTED";
}
