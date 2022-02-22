package jasmine.security.authorization.dynamic;

import jasmine.security.subject.UserSubject;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 检查指定用户是否有访问指定资源的权限。
 * </p>
 *
 * @author mh.z
 */
public interface DynamicAccessCheckService {

    /**
     * 检查权限
     *
     * @param subject
     * @param request
     * @return
     */
    boolean check(UserSubject subject, HttpServletRequest request);
}
