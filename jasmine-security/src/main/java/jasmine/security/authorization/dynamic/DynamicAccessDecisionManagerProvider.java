package jasmine.security.authorization.dynamic;

import jasmine.security.authorization.AccessDecisionManagerProvider;
import jasmine.security.authorization.AccessDecisionManagerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.Arrays;

/**
 * @author mh.z
 */
public class DynamicAccessDecisionManagerProvider implements AccessDecisionManagerProvider {
    private DynamicConfig securityConfig;
    private DynamicAccessCheckService checkService;

    public DynamicAccessDecisionManagerProvider(DynamicConfig securityConfig,
                                                @Autowired(required = false) DynamicAccessCheckService checkService) {
        this.securityConfig = securityConfig;
        this.checkService = checkService;
    }

    @Override
    public AccessDecisionManager getManager() {
        WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
        webExpressionVoter.setExpressionHandler(new OAuth2WebSecurityExpressionHandler());

        // 动态访问决策投票器
        DynamicAccessDecisionVoter dynamicVoter = new DynamicAccessDecisionVoter(securityConfig, checkService);
        // 访问决策管理器
        AccessDecisionManager manager = new AffirmativeBased(Arrays.asList(webExpressionVoter, dynamicVoter));
        AccessDecisionManagerProxy managerProxy = new AccessDecisionManagerProxy(manager);

        return managerProxy;
    }

}
