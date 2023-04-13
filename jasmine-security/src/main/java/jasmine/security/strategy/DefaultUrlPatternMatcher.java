package jasmine.security.strategy;

import jasmine.framework.context.InitSupport;
import jasmine.framework.context.RuntimeProvider;
import jasmine.framework.common.util.CheckUtil;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mh.z
 */
public class DefaultUrlPatternMatcher implements UrlPatternMatcher, InitSupport {
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void init(RuntimeProvider provider) {
        requestMappingHandlerMapping = provider.getByType(RequestMappingHandlerMapping.class);
    }

    @Override
    public String getUrlPattern(HttpServletRequest request) {
        CheckUtil.notNull(request, "request null");
        String urlPattern = SecurityResourceUtil.getUrlPattern(requestMappingHandlerMapping, request);

        return urlPattern;
    }

}
