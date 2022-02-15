package jasmine.demo.system.security;

import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.core.util.QObjectUtil;
import jasmine.core.util.QStringUtil;
import jasmine.security.authorization.RbacCheckService;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.rbac.constant.RbacConstants;
import jasmine.security.rbac.dto.ResourceBaseInfoDTO;
import jasmine.security.rbac.model.SecurityResource;
import jasmine.security.rbac.service.SecurityResourceService;
import jasmine.security.subject.UserSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public class CustomRbacCheckService implements RbacCheckService, InitSupport {
    private static final Logger logger = LoggerFactory.getLogger(CustomRbacCheckService.class);
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    private SecurityResourceService resourceService;

    public CustomRbacCheckService(SecurityResourceService resourceService) {
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

        // 判断是不是公开的资源
        if (isPublicResource(request)) {
            return true;
        }

        // 获取匹配的资源
        SecurityResource resource = getMatchedResource(request);
        if (resource == null) {
            return false;
        }

        // 判断是不是匿名就可以访问的资源
        String accessPolicy = resource.getAccessPolicy();
        if (RbacConstants.RESOURCE_ACCESS_POLICY_ANONYMOUS.equals(accessPolicy)) {
            return true;
        }

        // 判断是不是登录就可以访问的资源
        if (RbacConstants.RESOURCE_ACCESS_POLICY_AUTHENTICATED.equals(accessPolicy)) {
            return true;
        }

        // 判断是不是被授权访问的资源
        if (isGrantedResource(subject, resource)) {
            return true;
        }

        return false;
    }

    /**
     * 查找匹配的请求模式
     *
     * @param request
     * @return
     */
    protected String getMatchedPattern(HttpServletRequest request) {
        QCheckUtil.notNull(request, "request null");
        String pattern = null;

        try {
            requestMappingHandlerMapping.getHandler(request);
            pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        } catch (Exception e) {
            logger.warn("obtain matched pattern failed", e);
        }

        return pattern;
    }

    /**
     * 查找匹配到的请求资源
     *
     * @param request
     * @return
     */
    protected SecurityResource getMatchedResource(HttpServletRequest request) {
        QCheckUtil.notNull(request, "request null");

        // 获取匹配的请求模式
        String pattern = getMatchedPattern(request);
        if (pattern == null) {
            return null;
        }

        // 根据请求模式获取匹配的资源
        List<SecurityResource> resourceList = resourceService.listResourcesByPath(pattern);
        if (QCollectionUtil.isEmpty(resourceList)) {
            return null;
        }

        String requestMethod = request.getMethod();
        SecurityResource matchedResource = null;

        // 根据访问方式获取匹配的资源
        for (SecurityResource resource : resourceList) {
            String accessMethod = resource.getAccessMethod();

            if (RbacConstants.RESOURCE_ACCESS_METHOD_ANY.equals(accessMethod)
                    || QStringUtil.equals(requestMethod, accessMethod)) {
                matchedResource = resource;
                break;
            }
        }

        return matchedResource;
    }

    /**
     * 判断是不是公开的资源
     *
     * @param request
     * @return
     */
    protected boolean isPublicResource(HttpServletRequest request) {
        QCheckUtil.notNull(request, "request null");
        String servletPath = request.getServletPath();

        if ("".equals(servletPath) || "/".equals(servletPath)) {
            return true;
        }

        if (servletPath.startsWith("/static/")) {
            return true;
        }

        return false;
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

        // 获取授权
        Collection<GrantedAuthority> authorityList = subject.getAuthorities();
        if (QCollectionUtil.isEmpty(authorityList)) {
            return false;
        }

        // 获取角色ID
        List<Long> roleIdList = QCollectionUtil.mapToList(authorityList, (authority) -> {
            RoleAuthority roleAuthority = (RoleAuthority) authority;

            return roleAuthority.getRoleId();
        });

        // 获取角色授权的所有资源
        List<ResourceBaseInfoDTO> resourceBaseInfoList = resourceService
                .listResourceBaseInfoDTOsByRoleIds(roleIdList);
        if (QCollectionUtil.isEmpty(resourceBaseInfoList)) {
            return false;
        }

        // 判断该资源被授权访问
        for (ResourceBaseInfoDTO resourceBaseInfo : resourceBaseInfoList) {
            if (QObjectUtil.equals(resourceBaseInfo.getResourceId(), resourceId)) {
                return true;
            }
        }

        return false;
    }

}
