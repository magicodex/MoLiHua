package jasmine.demo.framework.security;

import cn.hutool.core.util.ObjectUtil;
import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.core.util.QStringUtil;
import jasmine.demo.framework.constant.FrameworkCaches;
import jasmine.framework.cache.CacheUtil;
import jasmine.security.authorization.DynamicAccessCheckService;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.rbac.constant.RbacConstants;
import jasmine.security.rbac.dto.SecurityFunctionBaseInfoDTO;
import jasmine.security.rbac.model.SecurityResource;
import jasmine.security.rbac.service.SecurityFunctionService;
import jasmine.security.rbac.service.SecurityResourceService;
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
public class RbacAccessCheckService implements DynamicAccessCheckService, InitSupport {
    private static final Logger logger = LoggerFactory.getLogger(RbacAccessCheckService.class);
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    private SecurityFunctionService functionService;
    private SecurityResourceService resourceService;

    public RbacAccessCheckService(RuntimeProvider runtimeProvider) {
        this.functionService = runtimeProvider.getByType(SecurityFunctionService.class);
        this.resourceService = runtimeProvider.getByType(SecurityResourceService.class);
    }

    @Override
    public void init(RuntimeProvider provider) {
        requestMappingHandlerMapping = provider.getByType(RequestMappingHandlerMapping.class);
    }

    @Override
    public boolean check(UserSubject subject, HttpServletRequest request) {
        QCheckUtil.notNull(subject, "subject null");
        QCheckUtil.notNull(request, "request null");

        // 获取匹配的资源
        SecurityResource resource = getMatchedResource(request);
        if (resource == null) {
            return false;
        }

        // 不鉴权匿名就可以访问的资源
        String accessPolicy = resource.getAccessPolicy();
        if (RbacConstants.RESOURCE_ACCESS_POLICY_ANONYMOUS.equals(accessPolicy)) {
            return true;
        }

        // 当前的用户是已登录的用户可以访问被标记成认证才能访问的资源
        if (RbacConstants.RESOURCE_ACCESS_POLICY_AUTHENTICATED.equals(accessPolicy)) {
            return true;
        }

        // 判断当前用户是否被授权访问该资源
        if (isGrantedResource(subject, resource)) {
            return true;
        }

        return false;
    }

    /**
     * 查找匹配到的请求资源
     *
     * @param request
     * @return
     */
    protected SecurityResource getMatchedResource(HttpServletRequest request) {
        QCheckUtil.notNull(request, "request null");

        // 计算请求的最佳匹配模式
        try {
            requestMappingHandlerMapping.getHandler(request);
        } catch (Exception e) {
            logger.warn("obtain matched pattern failed", e);
        }

        String requestPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        if (requestPattern == null) {
            return null;
        }

        String requestMethod = request.getMethod();
        // 根据请求查找资源
        SecurityResource matchedResource = getResourceByRequest(requestMethod, requestPattern);

        return matchedResource;
    }

    /**
     * 判断是不是被授权的资源
     *
     * @param subject
     * @param resource
     * @return
     */
    protected boolean isGrantedResource(UserSubject subject, SecurityResource resource) {
        QCheckUtil.notNull(subject, "subject null");
        QCheckUtil.notNull(resource, "resource null");
        Long resourceId = QCheckUtil.notNull(resource.getId(), "resource.id null");

        // 获取该用户被授予的所有功能
        List<SecurityFunctionBaseInfoDTO> userFunctionList = getFunctionsByUser(subject);
        if (QCollectionUtil.isEmpty(userFunctionList)) {
            return false;
        }

        // 查找该资源被授予给的所有功能
        List<SecurityFunctionBaseInfoDTO> resourceFunctionList = getFunctionsByResource(resourceId);
        if (QCollectionUtil.isEmpty(resourceFunctionList)) {
            return false;
        }

        // 判断该资源是否被授权访问
        for (int i = 0; i < userFunctionList.size(); i++) {
            for (int j = 0; j < resourceFunctionList.size(); j++) {
                Long userFunctionId = userFunctionList.get(i).getFunctionId();
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
    protected SecurityResource getResourceByRequest(String requestMethod, String urlPattern) {
        QCheckUtil.notNull(requestMethod, "requestMethod null");
        QCheckUtil.notNull(urlPattern, "urlPattern null");
        String cacheKey = requestMethod + "&" + urlPattern;

        SecurityResource resource = CacheUtil.get(FrameworkCaches.RESOURCE_WITH_REQUEST, cacheKey, () -> {
            // 获取指定路径的资源
            List<SecurityResource> resourceList = resourceService.listResourcesByPath(urlPattern);
            if (QCollectionUtil.isEmpty(resourceList)) {
                return null;
            }

            // 根据请求方法过滤资源
            for (SecurityResource current : resourceList) {
                String accessMethod = current.getAccessMethod();

                if (RbacConstants.RESOURCE_ACCESS_METHOD_ANY.equals(accessMethod)
                        || QStringUtil.equals(requestMethod, accessMethod)) {
                    return current;
                }
            }

            return null;
        }, SecurityResource.class);

        return resource;
    }

    /**
     * 查找指定用户被授予的所有功能
     *
     * @param subject
     * @return
     */
    protected List<SecurityFunctionBaseInfoDTO> getFunctionsByUser(UserSubject subject) {
        QCheckUtil.notNull(subject, "subject null");
        Long userId = QCheckUtil.notNull(subject.getUserId(), "subject.userId null");
        String category = FrameworkCaches.FUNCTIONS_WITH_USER_ID;

        List<SecurityFunctionBaseInfoDTO> functionList = CacheUtil.getList(category, userId, () -> {
            // 获取授权
            Collection<GrantedAuthority> authorityList = subject.getAuthorities();
            if (QCollectionUtil.isEmpty(authorityList)) {
                return Collections.emptyList();
            }

            // 获取角色ID
            List<Long> roleIdList = QCollectionUtil.mapToList(authorityList, (authority) -> {
                RoleAuthority roleAuthority = (RoleAuthority) authority;

                return roleAuthority.getRoleId();
            });

            // 获取角色被授权的所有功能
            return functionService.listFunctionBaseInfoDTOsByRoleIds(roleIdList);
        }, SecurityFunctionBaseInfoDTO.class);

        return functionList;
    }

    /**
     * 查找指定资源被授予给的所有功能
     *
     * @param resourceId
     * @return
     */
    protected List<SecurityFunctionBaseInfoDTO> getFunctionsByResource(Long resourceId) {
        QCheckUtil.notNull(resourceId, "resourceId null");
        String category = FrameworkCaches.FUNCTIONS_WITH_RESOURCE_ID;

        List<SecurityFunctionBaseInfoDTO> functionList = CacheUtil.getList(category, resourceId, () -> {
            return resourceService.listFunctionBaseInfoDTOsById(resourceId);
        }, SecurityFunctionBaseInfoDTO.class);

        return functionList;
    }

}
