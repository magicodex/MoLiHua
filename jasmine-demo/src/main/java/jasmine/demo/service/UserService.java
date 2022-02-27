package jasmine.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCollectionUtil;
import jasmine.demo.entity.User;
import jasmine.demo.mapper.UserMapper;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.rbac.model.SecRole;
import jasmine.security.rbac.service.SecRoleService;
import jasmine.security.subject.UserSubject;
import jasmine.security.subject.UserSubjectDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author mh.z
 */
@Component
public class UserService implements UserSubjectDetailsService, ClientDetailsService, InitSupport {
    private UserMapper userMapper;
    private SecRoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(RuntimeProvider provider) {
        this.userMapper = provider.getByType(UserMapper.class);
        this.passwordEncoder = provider.getByType(PasswordEncoder.class);
        this.roleService = provider.getByType(SecRoleService.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);

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
        User user = userMapper.selectById(userId);
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
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUserName, clientId);
        User user = userMapper.selectOne(wrapper);

        if (user == null) {
            return null;
        }

        // 获取角色
        Long userId = user.getId();
        List<GrantedAuthority> authorityList = getGrantedAuthorities(userId);

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

    /**
     * 查找指定用户已授予的角色
     *
     * @param userId
     * @return
     */
    protected List<GrantedAuthority> getGrantedAuthorities(Long userId) {
        // 获取角色
        List<SecRole> roleList = roleService.listRolesByUserId(userId);

        List<GrantedAuthority> authorityList = QCollectionUtil.mapToList(roleList, (role) -> {
            return new RoleAuthority(role.getId(), role.getRoleCode());
        });

        return authorityList;
    }

}
