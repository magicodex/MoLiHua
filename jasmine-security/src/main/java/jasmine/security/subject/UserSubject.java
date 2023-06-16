package jasmine.security.subject;

import jasmine.framework.common.constant.TextConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * <p>
 * 扩展用户对象。
 * </p>
 *
 * @author mh.z
 */
public class UserSubject extends User {
    /** 租户ID */
    private Long tenantId;
    /** 用户ID */
    private Long userId;

    public UserSubject(Long tenantId, Long userId) {
        this(tenantId, userId, TextConstants.UNKNOWN_PLACEHOLDER,
                TextConstants.UNKNOWN_PLACEHOLDER, Collections.emptyList());
    }

    public UserSubject(Long tenantId, Long userId, String username, String password,
                       Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.tenantId = tenantId;
        this.userId = userId;
    }

    public UserSubject(Long tenantId, Long userId, UserDetails details) {
        super(details.getUsername(), details.getPassword(), details.isEnabled(), details.isAccountNonExpired(),
                details.isCredentialsNonExpired(), details.isAccountNonLocked(), details.getAuthorities());
        this.tenantId = tenantId;
        this.userId = userId;
    }

    public Long getTenantId() {
        return tenantId;
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
