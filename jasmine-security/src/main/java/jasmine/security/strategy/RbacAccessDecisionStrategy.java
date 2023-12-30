package jasmine.security.strategy;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.CollectionUtil;
import jasmine.framework.common.util.StringUtil;
import jasmine.security.authorization.AccessDecisionStrategy;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.constant.SecurityConstants;
import jasmine.security.rbac.dto.SecResourceBaseInfoDTO;
import jasmine.security.subject.UserSubject;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author mh.z
 */
public class RbacAccessDecisionStrategy implements AccessDecisionStrategy {
    private RbacQueryService rbacQueryService;
    private UrlPatternMatcher urlPatternMatcher;

    public RbacAccessDecisionStrategy(RbacQueryService rbacQueryService,
                                      UrlPatternMatcher urlPatternMatcher) {
        this.rbacQueryService = rbacQueryService;
        this.urlPatternMatcher = urlPatternMatcher;
    }

    @Override
    public boolean check(UserSubject subject, HttpServletRequest request) {
        CheckUtil.notNull(subject, "subject null");
        CheckUtil.notNull(request, "request null");

        // 获取请求对应的 URL 模式
        String urlPattern = urlPatternMatcher.getUrlPattern(request);
        if (urlPattern == null) {
            return false;
        }

        // 获取请求对应的资源
        String requestMethod = StringUtil.orEmpty(request.getMethod());
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
        CheckUtil.notNull(subject, "subject null");
        CheckUtil.notNull(resource, "resource null");
        Long userId = CheckUtil.notNull(subject.getUserId(),
                "subject.userId null");
        Long resourceId = CheckUtil.notNull(resource.getResourceId(),
                "resource.resourceId null");

        Collection<GrantedAuthority> authorities = subject.getAuthorities();
        if (CollectionUtil.isEmpty(authorities)) {
            return false;
        }

        List<Long> roleIdList = CollectionUtil.mapToList(authorities, (authority) -> {
            return ((RoleAuthority) authority).getRoleId();
        });

        // 获取该资源被授予给的所有功能
        List<Long> resourceFunctionList = rbacQueryService.queryFunctionsByResource(resourceId);
        if (CollectionUtil.isEmpty(resourceFunctionList)) {
            return false;
        }

        for (int index = 0; index < roleIdList.size(); index++) {
            Long roleId = roleIdList.get(index);
            // 获取指定角色被授予的所有功能
            List<Long> roleFunctionList = rbacQueryService.queryFunctionsByRole(roleId);

            if (CollectionUtil.isNotEmpty(roleFunctionList)) {
                // 若两个集合存在交集则该用户允许访问该资源
                boolean checkResult = CollectionUtil.containsAny(roleFunctionList, resourceFunctionList);

                if (checkResult) {
                    return true;
                }
            }
        }

        return false;
    }


}
