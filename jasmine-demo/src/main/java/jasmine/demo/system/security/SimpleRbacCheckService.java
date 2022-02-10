package jasmine.demo.system.security;

import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollectionUtil;
import jasmine.core.util.QStringUtil;
import jasmine.security.authorization.RbacCheckService;
import jasmine.security.rbac.constant.RbacConstants;
import jasmine.security.rbac.model.SecurityFunctionPermission;
import jasmine.security.rbac.model.SecurityFunctionPermissionSet;
import jasmine.security.rbac.model.SecurityPermission;
import jasmine.security.rbac.model.SecurityPermissionSetPermission;
import jasmine.security.rbac.model.SecurityRoleFunction;
import jasmine.security.rbac.model.SecurityUserRole;
import jasmine.security.rbac.service.SecurityFunctionPermissionService;
import jasmine.security.rbac.service.SecurityFunctionPermissionSetService;
import jasmine.security.rbac.service.SecurityPermissionService;
import jasmine.security.rbac.service.SecurityPermissionSetPermissionService;
import jasmine.security.rbac.service.SecurityRoleFunctionService;
import jasmine.security.rbac.service.SecurityUserRoleService;
import jasmine.security.subject.UserSubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author mh.z
 */
@Service
public class SimpleRbacCheckService implements RbacCheckService, InitSupport {
    private static final Logger logger = LoggerFactory.getLogger(SimpleRbacCheckService.class);
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    private SecurityUserRoleService userRoleService;
    private SecurityRoleFunctionService roleFunctionService;
    private SecurityFunctionPermissionSetService functionPermissionSetService;
    private SecurityFunctionPermissionService functionPermissionService;
    private SecurityPermissionSetPermissionService permissionSetPermissionService;
    private SecurityPermissionService permissionService;
    /** 所有人都能访问的API */
    private static final Collection<String> ANYONE_CAN_ACCESS_APIS;

    public SimpleRbacCheckService(SecurityUserRoleService userRoleService,
                                  SecurityRoleFunctionService roleFunctionService,
                                  SecurityFunctionPermissionSetService functionPermissionSetService,
                                  SecurityFunctionPermissionService functionPermissionService,
                                  SecurityPermissionSetPermissionService permissionSetPermissionService,
                                  SecurityPermissionService permissionService) {
        this.userRoleService = userRoleService;
        this.roleFunctionService = roleFunctionService;
        this.functionPermissionSetService = functionPermissionSetService;
        this.functionPermissionService = functionPermissionService;
        this.permissionSetPermissionService = permissionSetPermissionService;
        this.permissionService = permissionService;
    }

    @Override
    public void init(RuntimeProvider provider) {
        requestMappingHandlerMapping = provider.getByType(RequestMappingHandlerMapping.class);
    }

    static {
        ANYONE_CAN_ACCESS_APIS = new HashSet<>();
        ANYONE_CAN_ACCESS_APIS.add("/");
        ANYONE_CAN_ACCESS_APIS.add("/login");
    }

    /**
     * 检查权限
     *
     * @param subject
     * @param request
     * @return
     */
    @Override
    public boolean check(UserSubject subject, HttpServletRequest request) {
        QCheckUtil.notNull(subject, "subject null");
        QCheckUtil.notNull(request, "request null");
        Long userId = QCheckUtil.notNull(subject.getUserId(), "subject.userId null");

        // 不鉴权静态资源
        String servletPath = request.getServletPath();
        if (servletPath.startsWith("/static/")) {
            return true;
        }

        // 不鉴权所有人都能访问的API
        if (ANYONE_CAN_ACCESS_APIS.contains(servletPath)) {
            return true;
        }

        // 查找该用户下的所有权限
        List<SecurityPermission> permissionList = getAllUserPermissions(userId);
        if (QCollectionUtil.isEmpty(permissionList)) {
            return false;
        }

        try {
            // 获取请求信息
            requestMappingHandlerMapping.getHandler(request);
            String pattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
            String method = request.getMethod();

            // 检查是否有访问该资源的权限
            for (SecurityPermission permission : permissionList) {
                String resourcePath = permission.getRequestResource();
                String resourceMethod = permission.getAccessType();
                boolean matchResult = QStringUtil.equals(pattern, resourcePath)
                        && (RbacConstants.REQUEST_METHOD_ANY.equals(resourceMethod)
                        || QStringUtil.equals(method, resourceMethod));

                if (matchResult) {
                    return true;
                }
            }
        } catch (Exception e) {
            logger.error("check failed", logger);
        }

        return false;
    }

    /**
     * 查找用户下的所有权限
     *
     * @param userId
     * @return
     */
    protected List<SecurityPermission> getAllUserPermissions(Long userId) {
        QCheckUtil.notNull(userId, "userId null");
        Set<Long> permissionIdSet = new HashSet<>();

        // 用户和角色的关系
        List<SecurityUserRole> userRoleList = userRoleService.listByUser(userId);
        List<Long> roleIdList = QCollectionUtil.mapToList(userRoleList, SecurityUserRole::getRoleId);

        // 角色和功能的关系
        List<SecurityRoleFunction> roleFunctionList = roleFunctionService.listByRoles(roleIdList);
        List<Long> functionIdList = QCollectionUtil.mapToList(roleFunctionList,
                SecurityRoleFunction::getFunctionId);

        // 功能和权限的关系
        List<SecurityFunctionPermission> functionPermissionList = functionPermissionService
                .listByFunctions(functionIdList);
        functionPermissionList.forEach((functionPermission) -> {
            permissionIdSet.add(functionPermission.getPermissionId());
        });

        // 功能和权限集的关系
        List<SecurityFunctionPermissionSet> functionPermissionSetList = functionPermissionSetService
                .listByFunctions(functionIdList);
        List<Long> permissionSetIdList = QCollectionUtil.mapToList(functionPermissionSetList,
                SecurityFunctionPermissionSet::getPermissionSetId);

        // 权限集和权限的关系
        if (QCollectionUtil.isNotEmpty(permissionSetIdList)) {
            List<SecurityPermissionSetPermission> permissionSetPermissionList = permissionSetPermissionService
                    .listByPermissionSets(permissionSetIdList);

            permissionSetPermissionList.forEach((permissionSetPermission) -> {
                permissionIdSet.add(permissionSetPermission.getPermissionId());
            });
        }

        if (QCollectionUtil.isEmpty(permissionIdSet)) {
            return Collections.emptyList();
        }

        return permissionService.listByIds(permissionIdSet);
    }

}