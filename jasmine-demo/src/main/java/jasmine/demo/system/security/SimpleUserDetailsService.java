package jasmine.demo.system.security;

import jasmine.core.context.RuntimeProvider;
import jasmine.demo.authentication.persistence.dao.UserDao;
import jasmine.demo.authentication.persistence.entity.UserEO;
import jasmine.security.subject.UserSubject;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * @author mh.z
 */
public class SimpleUserDetailsService implements UserDetailsService {
    private RuntimeProvider runtimeProvider;
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    public SimpleUserDetailsService(RuntimeProvider provider) {
        this.runtimeProvider = provider;
        userDao = runtimeProvider.getByType(UserDao.class);
        passwordEncoder = runtimeProvider.getByType(PasswordEncoder.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEO user = userDao.getUserByName(username);
        if (user == null) {
            return null;
        }

        UserSubject userDetails = new UserSubject(user.getTenantId(), user.getId(),
                user.getUserName(), passwordEncoder.encode(user.getPassword()),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        return userDetails;
    }

}
