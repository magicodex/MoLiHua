package jasmine.security.constant;

/**
 * @author mh.z
 */
public interface SecurityConstants {

    /** 资源访问方式-GET */
    String RESOURCE_ACCESS_METHOD_GET = "GET";

    /** 资源访问方式-POST */
    String RESOURCE_ACCESS_METHOD_POST = "POST";

    /** 资源访问方式-PUT */
    String RESOURCE_ACCESS_METHOD_PUT = "PUT";

    /** 资源访问方式-任意 */
    String RESOURCE_ACCESS_METHOD_ANY = "*";

    /** 资源访问策略-匿名 */
    String RESOURCE_ACCESS_POLICY_ANONYMOUS = "ANONYMOUS";

    /** 资源访问策略-已登录 */
    String RESOURCE_ACCESS_POLICY_AUTHENTICATED = "AUTHENTICATED";

    /** 资源访问策略-已授权 */
    String RESOURCE_ACCESS_POLICY_GRANTED = "GRANTED";

    /** 资源类型- */
    String RESOURCE_TYPE_API = "API";

    /** 资源类型- */
    String RESOURCE_TYPE_PAGE = "PAGE";
}
