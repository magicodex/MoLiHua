package jasmine.demo.framework.security;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCollectionUtil;
import jasmine.demo.authentication.persistence.dao.UserDao;
import jasmine.demo.authentication.persistence.entity.UserEO;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.rbac.model.SecurityRole;
import jasmine.security.rbac.service.SecurityRoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;
import java.util.List;

/**
 * @author mh.z
 */
public class BaseClientDetailsService implements ClientDetailsService {
    private RuntimeProvider runtimeProvider;
    private UserDao userDao;
    private SecurityRoleService roleService;
    private PasswordEncoder passwordEncoder;

    public BaseClientDetailsService(RuntimeProvider provider) {
        this.runtimeProvider = provider;
        userDao = runtimeProvider.getByType(UserDao.class);
        roleService = runtimeProvider.getByType(SecurityRoleService.class);
        passwordEncoder = runtimeProvider.getByType(PasswordEncoder.class);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        UserEO user = userDao.getUserByName(clientId);
        if (user == null) {
            return null;
        }

        // 获取角色
        Long userId = user.getId();
        List<SecurityRole> roleList = roleService.listRolesByUserId(userId);

        List<GrantedAuthority> authorityList = QCollectionUtil.mapToList(roleList, (role) -> {
            return new RoleAuthority(role.getId(), role.getRoleCode());
        });

        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(clientId);
        clientDetails.setClientSecret(passwordEncoder.encode(user.getPassword()));
        clientDetails.setAuthorities(authorityList);
        clientDetails.setAuthorizedGrantTypes(Arrays.asList("authorization_code", "password", "refresh_token"));
        // "CLIENT"是自定义，没有特殊含义
        clientDetails.setScope(Arrays.asList("CLIENT"));
        clientDetails.setAccessTokenValiditySeconds(3600);

        return clientDetails;
    }

}
