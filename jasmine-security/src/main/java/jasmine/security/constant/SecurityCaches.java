package jasmine.security.constant;

/**
 * @author mh.z
 */
public interface SecurityCaches {

    /** 通过资源 ID 缓存资源 */
    String RESOURCE_WITH_PATH_KEY = "jasmine.security.resourceWithPathKey";

    /** 通过资源 ID 缓存功能 */
    String FUNCTIONS_WITH_RESOURCE_ID = "jasmine.security.functionsWithResourceId";

    /** 通过角色 ID 缓存功能 */
    String FUNCTIONS_WITH_ROLE_ID = "jasmine.security.functionsWithRoleId";
}
