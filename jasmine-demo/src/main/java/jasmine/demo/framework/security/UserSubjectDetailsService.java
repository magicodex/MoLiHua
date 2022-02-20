package jasmine.demo.framework.security;

import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCollectionUtil;
import jasmine.demo.authentication.persistence.dao.UserDao;
import jasmine.demo.authentication.persistence.entity.UserEO;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.rbac.model.SecurityRole;
import jasmine.security.rbac.service.SecurityRoleService;
import jasmine.security.subject.UserSubject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * @author mh.z
 */
public class UserSubjectDetailsService implements UserDetailsService {
    private RuntimeProvider runtimeProvider;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private SecurityRoleService roleService;

    public UserSubjectDetailsService(RuntimeProvider provider) {
        this.runtimeProvider = provider;
        userDao = runtimeProvider.getByType(UserDao.class);
        passwordEncoder = runtimeProvider.getByType(PasswordEncoder.class);
        roleService = runtimeProvider.getByType(SecurityRoleService.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取用户
        UserEO user = userDao.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("username '" + username + "' not found");
        }

        // 获取角色
        Long userId = user.getId();
        List<SecurityRole> roleList = roleService.listRolesByUserId(userId);

        List<GrantedAuthority> authorityList = QCollectionUtil.mapToList(roleList, (role) -> {
            return new RoleAuthority(role.getId(), role.getRoleCode());
        });

        UserSubject userDetails = new UserSubject(user.getTenantId(), userId,
                user.getUserName(), passwordEncoder.encode(user.getPassword()), authorityList);

        return userDetails;
    }

}
