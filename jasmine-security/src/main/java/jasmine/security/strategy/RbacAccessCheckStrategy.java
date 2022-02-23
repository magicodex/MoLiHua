package jasmine.security.strategy;

import cn.hutool.core.util.ObjectUtil;
import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.core.util.QStringUtil;
import jasmine.framework.cache.CacheUtil;
import jasmine.security.authorization.AccessDecisionStrategy;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.constant.SecurityCaches;
import jasmine.security.constant.SecurityConstants;
import jasmine.security.rbac.dto.SecFunctionBaseInfoDTO;
import jasmine.security.rbac.model.SecResource;
import jasmine.security.rbac.service.SecFunctionService;
import jasmine.security.rbac.service.SecResourceService;
import jasmine.security.subject.UserSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author mh.z
 */
public class RbacAccessCheckStrategy implements AccessDecisionStrategy, InitSupport {
    private static final Logger logger = LoggerFactory.getLogger(RbacAccessCheckStrategy.class);
    private SecFunctionService functionService;
    private SecResourceService resourceService;
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    public RbacAccessCheckStrategy(SecFunctionService functionService,
                                   SecResourceService resourceService) {
        this.functionService = functionService;
        this.resourceService = resourceService;
    }

    @Override
    public void init(RuntimeProvider provider) {
        requestMappingHandlerMapping = provider.getByType(RequestMappingHandlerMapping.class);
    }

    @Override
    public boolean check(UserSubject subject, HttpServletRequest request) {
        QCheckUtil.notNull(subject, "subject null");
        QCheckUtil.notNull(request, "request null");
        String servletPath = request.getServletPath();

        if (servletPath.isEmpty() || servletPath.equals("/")) {
            return true;
        }

        // 获取请求对应的资源
        SecResource resource = getMatchedResource(request);
        if (resource == null) {
            return false;
        }

        // 不鉴权匿名就可以访问的资源
        String accessPolicy = resource.getAccessPolicy();
        if (SecurityConstants.RESOURCE_ACCESS_POLICY_ANONYMOUS.equals(accessPolicy)) {
            return true;
        }

        // 当前用户是已登录的用户可以访问被标记成认证才能访问的资源
        if (SecurityConstants.RESOURCE_ACCESS_POLICY_AUTHENTICATED.equals(accessPolicy)) {
            return true;
        }

        // 当前用户可以访问被授予的资源
        if (isGrantedResource(subject, resource)) {
            return true;
        }

        return false;
    }

    /**
     * 查找请求对应的资源
     *
     * @param request
     * @return
     */
    protected SecResource getMatchedResource(HttpServletRequest request) {
        QCheckUtil.notNull(request, "request null");

        // 计算请求的最佳匹配模式
        try {
            requestMappingHandlerMapping.getHandler(request);
        } catch (Exception e) {
            logger.warn("RequestMappingHandlerMapping.getHandler failed", e);
        }

        String urlPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        if (urlPattern == null) {
            return null;
        }

        String requestMethod = request.getMethod();
        // 获取请求对应的资源
        SecResource resource = getResourceByRequest(requestMethod, urlPattern);

        return resource;
    }

    /**
     * 判断该资源是否被授予给指定用户
     *
     * @param subject
     * @param resource
     * @return
     */
    protected boolean isGrantedResource(UserSubject subject, SecResource resource) {
        QCheckUtil.notNull(subject, "subject null");
        QCheckUtil.notNull(resource, "resource null");
        Long resourceId = QCheckUtil.notNull(resource.getId(), "resource.id null");

        // 获取该用户被授予的所有功能
        List<SecFunctionBaseInfoDTO> userFunctionList = getFunctionsByUser(subject);
        if (QCollectionUtil.isEmpty(userFunctionList)) {
            return false;
        }

        // 获取该资源被授予给的所有功能
        List<SecFunctionBaseInfoDTO> resourceFunctionList = getFunctionsByResource(resourceId);
        if (QCollectionUtil.isEmpty(resourceFunctionList)) {
            return false;
        }

        // 若两个集合存在交集则该用户允许访问该资源
        for (int i = 0; i < userFunctionList.size(); i++) {
            Long userFunctionId = userFunctionList.get(i).getFunctionId();

            for (int j = 0; j < resourceFunctionList.size(); j++) {
                Long resourceFunctionId = resourceFunctionList.get(j).getFunctionId();

                if (ObjectUtil.equals(userFunctionId, resourceFunctionId)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 查找请求对应的资源
     *
     * @param requestMethod
     * @param urlPattern
     * @return
     */
    protected SecResource getResourceByRequest(String requestMethod, String urlPattern) {
        QCheckUtil.notNull(requestMethod, "requestMethod null");
        QCheckUtil.notNull(urlPattern, "urlPattern null");
        String cacheKey = requestMethod + "&" + urlPattern;

        SecResource resource = CacheUtil.get(SecurityCaches.RESOURCE_WITH_REQUEST, cacheKey, () -> {
            // 获取指定路径的资源
            List<SecResource> resourceList = resourceService.listResourcesByPath(urlPattern);
            if (QCollectionUtil.isEmpty(resourceList)) {
                return null;
            }

            // 根据请求方法过滤资源
            for (SecResource current : resourceList) {
                String accessMethod = current.getAccessMethod();

                if (SecurityConstants.RESOURCE_ACCESS_METHOD_ANY.equals(accessMethod)
                        || QStringUtil.equals(requestMethod, accessMethod)) {
                    return current;
                }
            }

            return null;
        }, SecResource.class);

        return resource;
    }

    /**
     * 查找指定用户被授予的所有功能
     *
     * @param subject
     * @return
     */
    protected List<SecFunctionBaseInfoDTO> getFunctionsByUser(UserSubject subject) {
        QCheckUtil.notNull(subject, "subject null");
        Long userId = QCheckUtil.notNull(subject.getUserId(), "subject.userId null");
        String category = SecurityCaches.FUNCTIONS_WITH_USER_ID;

        List<SecFunctionBaseInfoDTO> functionList = CacheUtil.getList(category, userId, () -> {
            // 获取该用户的授权对象
            Collection<GrantedAuthority> authorityList = subject.getAuthorities();
            if (QCollectionUtil.isEmpty(authorityList)) {
                return Collections.emptyList();
            }

            // 获取角色ID
            List<Long> roleIdList = QCollectionUtil.mapToList(authorityList, (authority) -> {
                RoleAuthority roleAuthority = (RoleAuthority) authority;

                return roleAuthority.getRoleId();
            });

            // 获取角色被授予的所有功能
            return functionService.listFunctionBaseInfoDTOsByRoleIds(roleIdList);
        }, SecFunctionBaseInfoDTO.class);

        return functionList;
    }

    /**
     * 查找指定资源被授予给的所有功能
     *
     * @param resourceId
     * @return
     */
    protected List<SecFunctionBaseInfoDTO> getFunctionsByResource(Long resourceId) {
        QCheckUtil.notNull(resourceId, "resourceId null");
        String category = SecurityCaches.FUNCTIONS_WITH_RESOURCE_ID;

        List<SecFunctionBaseInfoDTO> functionList = CacheUtil.getList(category, resourceId, () -> {
            // 获取该资源被授予给的所有功能
            return resourceService.listFunctionBaseInfoDTOsById(resourceId);
        }, SecFunctionBaseInfoDTO.class);

        return functionList;
    }

}
