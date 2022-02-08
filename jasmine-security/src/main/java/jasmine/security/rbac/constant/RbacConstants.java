package jasmine.security.rbac.constant;

/**
 * @author mh.z
 */
public interface RbacConstants {

    /** 请求方法-GET */
    String REQUEST_METHOD_GET = "GET";

    /** 请求方法-POST */
    String REQUEST_METHOD_POST = "POST";

    /** 请求方法-PUT */
    String REQUEST_METHOD_PUT = "PUT";

    /** 请求方法-任意 */
    String REQUEST_METHOD_ANY = "*";

    /** 访问类型-公开，所有人都能访问 */
    String ACCESS_TYPE_PUBLIC = "PUBLIC";

    /** 访问类型-已登录，已登录的就可访问 */
    String ACCESS_TYPE_AUTHENTICATED = "AUTHENTICATED";

    /** 访问类型-已授权，已授权的才能访问 */
    String ACCESS_TYPE_GRANTED = "GRANTED";
}
