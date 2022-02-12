package jasmine.security.rbac.constant;

/**
 * @author mh.z
 */
public interface RbacConstants {

    /** 请求方法-GET */
    String RESOURCE_METHOD_GET = "GET";

    /** 请求方法-POST */
    String RESOURCE_METHOD_POST = "POST";

    /** 请求方法-PUT */
    String RESOURCE_METHOD_PUT = "PUT";

    /** 请求方法-任意 */
    String RESOURCE_METHOD_ANY = "*";

    /** 访问类型-公开，所有人都能访问 */
    String ACCESS_POLICY_ANONYMOUS = "ANONYMOUS";

    /** 访问类型-已登录，已登录的就可访问 */
    String ACCESS_POLICY_AUTHENTICATED = "AUTHENTICATED";

    /** 访问类型-已授权，已授权的才能访问 */
    String ACCESS_POLICY_GRANTED = "GRANTED";
}
