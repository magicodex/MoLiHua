package jasmine.demo.service;

import jasmine.demo.entity.User;
import jasmine.demo.mapper.UserMapper;
import jasmine.framework.common.util.CollectionUtil;
import jasmine.framework.context.InitSupport;
import jasmine.framework.context.RuntimeProvider;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.rbac.dao.SecRoleDAO;
import jasmine.security.rbac.model.SecRole;
import jasmine.security.subject.ClientSubject;
import jasmine.security.subject.ClientSubjectDetailsService;
import jasmine.security.subject.UserSubject;
import jasmine.security.subject.UserSubjectDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author mh.z
 */
@Component
public class UserService implements UserSubjectDetailsService, ClientSubjectDetailsService, InitSupport {
    private UserMapper userMapper;
    private SecRoleDAO roleDAO;
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(RuntimeProvider provider) {
        this.userMapper = provider.getByType(UserMapper.class);
        this.passwordEncoder = provider.getByType(PasswordEncoder.class);
        this.roleDAO = provider.getByType(SecRoleDAO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getAllTenantUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("username '" + username + "' not found");
        }

        // 获取角色
        Long userId = user.getId();
        List<GrantedAuthority> authorityList = getGrantedAuthorities(userId);

        UserSubject userDetails = new UserSubject(user.getTenantId(), userId,
                user.getUserName(), passwordEncoder.encode(user.getPassword()), authorityList);

        return userDetails;
    }

    @Override
    public UserSubject loadUserByUserId(Long userId) throws UsernameNotFoundException {
        // 获取用户
        User user = userMapper.getAllTenantUserById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("userId '" + userId + "' not found");
        }

        // 获取角色
        List<GrantedAuthority> authorityList = getGrantedAuthorities(userId);

        UserSubject userDetails = new UserSubject(user.getTenantId(), userId,
                user.getUserName(), passwordEncoder.encode(user.getPassword()), authorityList);

        return userDetails;
    }

    @Override
    public ClientSubject loadClientByClientId(String clientId) throws ClientRegistrationException {
        User user = userMapper.getAllTenantUserByName(clientId);
        if (user == null) {
            return null;
        }

        // 获取角色
        Long userId = user.getId();
        List<GrantedAuthority> authorityList = getGrantedAuthorities(userId);

        ClientSubject clientDetails = new ClientSubject();
        clientDetails.setClientId(clientId);
        clientDetails.setClientSecret(passwordEncoder.encode(user.getPassword()));
        clientDetails.setAuthorities(authorityList);
        clientDetails.setAuthorizedGrantTypes(Arrays.asList("authorization_code", "password", "refresh_token"));
        // "CLIENT"是自定义，没有特殊含义
        clientDetails.setScope(Arrays.asList("CLIENT"));
        clientDetails.setAccessTokenValiditySeconds(3600);

        return clientDetails;
    }

    /**
     * 查找指定用户已授予的角色
     *
     * @param userId
     * @return
     */
    protected List<GrantedAuthority> getGrantedAuthorities(Long userId) {
        // 获取角色
        List<SecRole> roleList = roleDAO.listAllTenantRolesByUserIdNoI18n(userId);

        List<GrantedAuthority> authorityList = CollectionUtil.mapToList(roleList, (role) -> {
            return new RoleAuthority(role.getId(), role.getRoleCode());
        });

        return authorityList;
    }

}
