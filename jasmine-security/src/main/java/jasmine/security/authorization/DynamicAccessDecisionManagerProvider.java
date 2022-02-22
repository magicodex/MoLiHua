package jasmine.security.authorization;

import jasmine.security.config.JasmineSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author mh.z
 */
@Component
public class DynamicAccessDecisionManagerProvider implements AccessDecisionManagerProvider {
    private JasmineSecurityConfig securityConfig;
    private DynamicAccessCheckService checkService;

    public DynamicAccessDecisionManagerProvider(JasmineSecurityConfig securityConfig,
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
