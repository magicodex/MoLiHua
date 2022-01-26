package jasmine.demo.authentication.business.service;

import jasmine.demo.framework.security.UserSubject;
import jasmine.demo.authentication.persistence.dao.DemoUserDao;
import jasmine.demo.authentication.persistence.entity.DemoUserEntity;
import jasmine.core.context.RuntimeProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * @author mh.z
 */
public class DemoUserDetailsService implements UserDetailsService {
    private DemoUserDao userDao;
    private PasswordEncoder passwordEncoder;

    public DemoUserDetailsService(RuntimeProvider provider) {
        userDao = provider.getByType(DemoUserDao.class);
        passwordEncoder = provider.getByType(PasswordEncoder.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("MoLiHua".equals(username)) {
            UserSubject userDetails = new UserSubject(100001L, "MoLiHua",
                    passwordEncoder.encode("123456"),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

            return userDetails;
        }

        DemoUserEntity user = userDao.getUserByName(username);
        if (user == null) {
            return null;
        }

        UserSubject userDetails = new UserSubject(user.getId(), user.getUserName(),
                passwordEncoder.encode(user.getPassword()),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

        return userDetails;
    }

}
