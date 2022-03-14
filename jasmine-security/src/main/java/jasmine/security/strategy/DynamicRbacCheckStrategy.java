package jasmine.security.strategy;

import jasmine.security.authorization.AccessDecisionStrategy;
import jasmine.security.subject.UserSubject;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mh.z
 */
public class DynamicRbacCheckStrategy implements AccessDecisionStrategy {

    @Override
    public boolean check(UserSubject subject, HttpServletRequest request) {
        return false;
    }

}
