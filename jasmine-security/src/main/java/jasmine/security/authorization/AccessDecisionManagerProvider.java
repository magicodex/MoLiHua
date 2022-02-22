package jasmine.security.authorization;

import org.springframework.security.access.AccessDecisionManager;

/**
 * @author mh.z
 */
public interface AccessDecisionManagerProvider {

    /**
     * 返回对象
     *
     * @return
     */
    AccessDecisionManager getManager();
}
