package jasmine.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jasmine.core.util.QCollectionUtil;
import jasmine.demo.entity.User;
import jasmine.demo.mapper.UserMapper;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.rbac.model.SecRole;
import jasmine.security.rbac.service.SecRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
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
@RequiredArgsConstructor
@Component
public class ClientDetailsServiceImpl implements ClientDetailsService {
    private final UserMapper userMapper;
    private final SecRoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public ClientDetailsServiceImpl(UserMapper userMapper, SecRoleService roleService,
                                    PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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
        List<SecRole> roleList = roleService.listRolesByUserId(userId);

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
