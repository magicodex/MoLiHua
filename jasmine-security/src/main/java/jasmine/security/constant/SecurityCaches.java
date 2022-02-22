package jasmine.security.constant;

/**
 * @author mh.z
 */
public interface SecurityCaches {

    /** 通过资源 ID 缓存资源 */
    String RESOURCE_WITH_REQUEST = "RESOURCE_WITH_ID";

    /** 通过资源 ID 缓存功能 */
    String FUNCTIONS_WITH_RESOURCE_ID = "FUNCTIONS_WITH_RESOURCE_ID";

    /** 通过用户 ID 缓存功能 */
    String FUNCTIONS_WITH_USER_ID = "FUNCTIONS_WITH_USER_ID";
}
