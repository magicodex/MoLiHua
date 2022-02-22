package jasmine.security.subject;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author mh.z
 */
public interface UserSubjectDetailsService extends UserDetailsService {

    /**
     * 根据用户 ID 查找用户
     *
     * @param userId
     * @return
     */
    UserSubject loadUserByUserId(Long userId) throws UsernameNotFoundException;
}
