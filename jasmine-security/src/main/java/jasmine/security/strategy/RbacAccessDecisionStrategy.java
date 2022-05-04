package jasmine.security.strategy;

import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.core.util.QStringUtil;
import jasmine.security.authorization.AccessDecisionStrategy;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.constant.SecurityConstants;
import jasmine.security.rbac.dto.SecResourceBaseInfoDTO;
import jasmine.security.subject.UserSubject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public class RbacAccessDecisionStrategy implements AccessDecisionStrategy, InitSupport {
    private RbacQueryService rbacQueryService;
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    private static final String SLASH_SYMBOL = "/";

    public RbacAccessDecisionStrategy(RbacQueryService rbacQueryService) {
        this.rbacQueryService = rbacQueryService;
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

        if (servletPath.isEmpty() || servletPath.equals(SLASH_SYMBOL)) {
            return true;
        }

        // 获取请求对应的 URL 模式
        String urlPattern = SecurityResourceUtil.getUrlPattern(requestMappingHandlerMapping, request);
        if (urlPattern == null) {
            return false;
        }

        // 获取请求对应的资源
        String requestMethod = QStringUtil.orEmpty(request.getMethod());
        SecResourceBaseInfoDTO resource = rbacQueryService.queryResourceByRequest(requestMethod, urlPattern);
        if (resource == null) {
            return false;
        }

        // 不允许访问被冻结的资源
        if (Boolean.TRUE.equals(resource.getFrozenFlag())) {
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
        if (checkGrant(subject, resource)) {
            return true;
        }

        return false;
    }

    /**
     * 判断该资源是否被授予给指定用户
     *
     * @param subject
     * @param resource
     * @return
     */
    protected boolean checkGrant(UserSubject subject, SecResourceBaseInfoDTO resource) {
        QCheckUtil.notNull(subject, "subject null");
        QCheckUtil.notNull(resource, "resource null");
        Long userId = QCheckUtil.notNull(subject.getUserId(),
                "subject.userId null");
        Long resourceId = QCheckUtil.notNull(resource.getResourceId(),
                "resource.resourceId null");

        Collection<GrantedAuthority> authorities = subject.getAuthorities();
        if (QCollectionUtil.isEmpty(authorities)) {
            return false;
        }

        List<Long> roleIdList = QCollectionUtil.mapToList(authorities, (authority) -> {
            return ((RoleAuthority) authority).getRoleId();
        });

        // 获取该用户被授予的所有功能
        List<Long> userFunctionList = rbacQueryService.queryFunctionsByUser(userId, roleIdList);
        if (QCollectionUtil.isEmpty(userFunctionList)) {
            return false;
        }

        // 获取该资源被授予给的所有功能
        List<Long> resourceFunctionList = rbacQueryService.queryFunctionsByResource(resourceId);
        if (QCollectionUtil.isEmpty(resourceFunctionList)) {
            return false;
        }

        // 若两个集合存在交集则该用户允许访问该资源
        boolean checkResult = QCollectionUtil.containsAny(userFunctionList, resourceFunctionList);

        return checkResult;
    }


}
