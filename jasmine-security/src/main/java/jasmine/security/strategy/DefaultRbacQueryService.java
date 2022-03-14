package jasmine.security.strategy;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.core.util.QStringUtil;
import jasmine.security.constant.SecurityConstants;
import jasmine.security.rbac.dao.SecFunctionDao;
import jasmine.security.rbac.dao.SecResourceDao;
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
    private SecFunctionDao functionDao;
    private SecResourceDao resourceDao;

    public DefaultRbacQueryService(SecFunctionDao functionDao, SecResourceDao resourceDao) {
        this.functionDao = functionDao;
        this.resourceDao = resourceDao;
    }

    @Override
    public SecResourceBaseInfoDTO queryResourceByRequest(String requestMethod, String urlPattern) {
        QCheckUtil.notNull(requestMethod, "requestMethod null");
        QCheckUtil.notNull(urlPattern, "urlPattern null");

        // 获取指定路径的资源
        List<SecResource> resourceList = resourceDao.listResourcesByPath(urlPattern);
        if (QCollectionUtil.isEmpty(resourceList)) {
            return null;
        }

        // 根据请求方法过滤资源
        for (SecResource current : resourceList) {
            String accessMethod = current.getAccessMethod();

            if (SecurityConstants.RESOURCE_ACCESS_METHOD_ANY.equals(accessMethod)
                    || QStringUtil.equals(requestMethod, accessMethod)) {
                return SecResourceUtil.toResourceBaseInfoDTO(current);
            }
        }

        return null;
    }

    @Override
    public List<Long> queryFunctionsByUser(Long userId, Collection<Long> roleIds) {
        QCheckUtil.notNull(userId, "userId null");
        QCheckUtil.notNull(roleIds, "roleIds null");

        List<Long> roleIdList = QCollectionUtil.castToList(roleIds);
        // 获取角色被授予的所有功能
        List<SecFunctionBaseInfoDTO> functionList = functionDao
                .listAllTenantFunctionBaseInfoDTOsByRoleIds(roleIdList);

        List<Long> functionIdList = QCollectionUtil.mapToList(functionList,
                SecFunctionBaseInfoDTO::getFunctionId);

        return functionIdList;
    }

    @Override
    public List<Long> queryFunctionsByResource(Long resourceId) {
        QCheckUtil.notNull(resourceId, "resourceId null");

        // 获取该资源被授予给的所有功能
        List<SecFunctionBaseInfoDTO> functionList = resourceDao
                .listFunctionBaseInfoDTOsById(resourceId);
        if (QCollectionUtil.isEmpty(functionList)) {
            return Collections.emptyList();
        }

        List<Long> functionIdList = QCollectionUtil.mapToList(functionList,
                SecFunctionBaseInfoDTO::getFunctionId);

        return functionIdList;
    }

}