package jasmine.demo.framework.security;

import jasmine.core.context.InitSupport;
import jasmine.core.context.RuntimeProvider;
import jasmine.core.util.QCollectionUtil;
import jasmine.demo.authentication.persistence.dao.UserDao;
import jasmine.demo.authentication.persistence.entity.UserEO;
import jasmine.security.authorization.RoleAuthority;
import jasmine.security.rbac.model.SecRole;
import jasmine.security.rbac.service.SecRoleService;
import jasmine.security.subject.UserSubject;
import jasmine.security.subject.UserSubjectDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author mh.z
 */
@Component
public class UserSubjectDetailsServiceImpl implements UserSubjectDetailsService, InitSupport {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    private SecRoleService roleService;

    @Override
    public void init(RuntimeProvider provider) {
        this.userDao = provider.getByType(UserDao.class);
        this.passwordEncoder = provider.getByType(PasswordEncoder.class);
        this.roleService = provider.getByType(SecRoleService.class);
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
        List<GrantedAuthority> authorityList = getGrantedAuthorities(userId);

        UserSubject userDetails = new UserSubject(user.getTenantId(), userId,
                user.getUserName(), passwordEncoder.encode(user.getPassword()), authorityList);

        return userDetails;
    }

    @Override
    public UserSubject loadUserByUserId(Long userId) throws UsernameNotFoundException {
        // 获取用户
        UserEO user = userDao.getUserById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("userId '" + userId + "' not found");
        }

        // 获取角色
        List<GrantedAuthority> authorityList = getGrantedAuthorities(userId);

        UserSubject userDetails = new UserSubject(user.getTenantId(), userId,
                user.getUserName(), passwordEncoder.encode(user.getPassword()), authorityList);

        return userDetails;
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
