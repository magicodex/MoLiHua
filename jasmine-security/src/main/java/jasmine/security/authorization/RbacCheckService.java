package jasmine.security.authorization;

import jasmine.security.subject.UserSubject;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mh.z
 */
public interface RbacCheckService {

    /**
     * 检查权限
     *
     * @param subject
     * @param request
     * @return
     */
    boolean check(UserSubject subject, HttpServletRequest request);
}
