package jasmine.security.strategy;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.CollectionUtil;
import jasmine.framework.common.util.StringUtil;
import jasmine.security.constant.SecurityConstants;
import jasmine.security.rbac.dao.SecFunctionDAO;
import jasmine.security.rbac.dao.SecResourceDAO;
import jasmine.security.rbac.dto.SecFunctionBaseInfoDTO;
import jasmine.security.rbac.dto.SecResourceBaseInfoDTO;
import jasmine.security.rbac.model.SecResource;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
public class DefaultRbacQueryService implements RbacQueryService {
    private SecFunctionDAO functionDAO;
    private SecResourceDAO resourceDAO;

    public DefaultRbacQueryService(SecFunctionDAO functionDAO, SecResourceDAO resourceDAO) {
        this.functionDAO = functionDAO;
        this.resourceDAO = resourceDAO;
    }

    @Override
    public SecResourceBaseInfoDTO queryResourceByRequest(String requestMethod, String urlPattern) {
        CheckUtil.notNull(requestMethod, "requestMethod null");
        CheckUtil.notNull(urlPattern, "urlPattern null");

        // 获取指定路径的资源
        List<SecResource> resourceList = resourceDAO.listResourcesByPathNoI18n(urlPattern);
        if (CollectionUtil.isEmpty(resourceList)) {
            return null;
        }

        // 根据请求方法过滤资源
        for (SecResource current : resourceList) {
            String accessMethod = current.getAccessMethod();

            if (SecurityConstants.RESOURCE_ACCESS_METHOD_ANY.equals(accessMethod)
                    || StringUtil.equals(requestMethod, accessMethod)) {
                return SecurityResourceUtil.toResourceBaseInfoDTO(current);
            }
        }

        return null;
    }

    @Override
    public List<Long> queryFunctionsByUser(Long userId, Collection<Long> roleIds) {
        CheckUtil.notNull(userId, "userId null");
        CheckUtil.notNull(roleIds, "roleIds null");

        List<Long> roleIdList = CollectionUtil.toList(roleIds);
        // 获取角色被授予的所有功能
        List<SecFunctionBaseInfoDTO> functionList = functionDAO
                .listAllTenantFunctionBaseInfoDTOsByRoleIdsNoI18n(roleIdList);

        List<Long> functionIdList = CollectionUtil.mapToList(functionList,
                SecFunctionBaseInfoDTO::getFunctionId);

        return functionIdList;
    }

    @Override
    public List<Long> queryFunctionsByResource(Long resourceId) {
        CheckUtil.notNull(resourceId, "resourceId null");

        // 获取该资源被授予给的所有功能
        List<SecFunctionBaseInfoDTO> functionList = functionDAO
                .listFunctionBaseInfoDTOsByIdNoI18n(resourceId);
        if (CollectionUtil.isEmpty(functionList)) {
            return Collections.emptyList();
        }

        List<Long> functionIdList = CollectionUtil.mapToList(functionList,
                SecFunctionBaseInfoDTO::getFunctionId);

        return functionIdList;
    }

}
