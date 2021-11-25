package jasmine.framework.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * <p>
 * 扩展用户对象。
 * </p>
 *
 * @author mh.z
 */
public class UserSubject extends User {
    /** 用户ID */
    private Long userId;

    public UserSubject(Long userId, String username, String password,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
    }

    /**
     * 返回用户ID
     *
     * @return
     */
    public Long getUserId() {
        return userId;
    }

}
