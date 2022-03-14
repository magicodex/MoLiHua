package jasmine.security.strategy;

import jasmine.security.rbac.dto.SecResourceBaseInfoDTO;

import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public interface RbacQueryService {

    /**
     * 查找请求的资源
     *
     * @param requestMethod
     * @param urlPattern
     * @return
     */
    SecResourceBaseInfoDTO queryResourceByRequest(String requestMethod, String urlPattern);

    /**
     * 查找被授予的功能
     *
     * @param userId
     * @param roleIds
     * @return
     */
    List<Long> queryFunctionsByUser(Long userId, Collection<Long> roleIds);

    /**
     * 查找被授予给的功能
     *
     * @param resourceId
     * @return
     */
    List<Long> queryFunctionsByResource(Long resourceId);
}
