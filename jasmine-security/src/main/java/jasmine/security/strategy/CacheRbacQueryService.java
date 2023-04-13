package jasmine.security.strategy;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.cache.CacheUtil;
import jasmine.security.constant.SecurityCaches;
import jasmine.security.rbac.dao.SecFunctionDAO;
import jasmine.security.rbac.dao.SecResourceDAO;
import jasmine.security.rbac.dto.SecResourceBaseInfoDTO;

import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public class CacheRbacQueryService extends DefaultRbacQueryService {

    public CacheRbacQueryService(SecFunctionDAO functionDAO, SecResourceDAO resourceDAO) {
        super(functionDAO, resourceDAO);
    }

    @Override
    public SecResourceBaseInfoDTO queryResourceByRequest(String requestMethod, String urlPattern) {
        CheckUtil.notNull(requestMethod, "requestMethod null");
        CheckUtil.notNull(urlPattern, "urlPattern null");
        String cacheKey = requestMethod + "&" + urlPattern;

        SecResourceBaseInfoDTO resource = CacheUtil.get(SecurityCaches.RESOURCE_WITH_REQUEST, cacheKey, () -> {
            return super.queryResourceByRequest(requestMethod, urlPattern);
        }, SecResourceBaseInfoDTO.class);

        return resource;
    }

    @Override
    public List<Long> queryFunctionsByUser(Long userId, Collection<Long> roleIds) {
        CheckUtil.notNull(userId, "userId null");
        CheckUtil.notNull(roleIds, "roleIds null");

        List<Long> functionIdList = CacheUtil.getList(SecurityCaches.FUNCTIONS_WITH_USER_ID, userId, () -> {
            return super.queryFunctionsByUser(userId, roleIds);
        }, Long.class);

        return functionIdList;
    }

    @Override
    public List<Long> queryFunctionsByResource(Long resourceId) {
        CheckUtil.notNull(resourceId, "resourceId null");

        List<Long> functionIdList = CacheUtil.getList(SecurityCaches.FUNCTIONS_WITH_RESOURCE_ID, resourceId, () -> {
            return super.queryFunctionsByResource(resourceId);
        }, Long.class);

        return functionIdList;
    }

}
