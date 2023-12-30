package jasmine.security.strategy;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.NewUtil;
import jasmine.framework.common.util.StringUtil;
import jasmine.framework.context.InitSupport;
import jasmine.framework.context.RuntimeProvider;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mh.z
 */
public class DefaultUrlPatternMatcher implements UrlPatternMatcher, InitSupport {
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    private Map<String, String> customUrlPatternMap;

    private static final String SLASH_SYMBOL = "/";

    public DefaultUrlPatternMatcher() {
        customUrlPatternMap = NewUtil.map();
        customUrlPatternMap.put(SLASH_SYMBOL, SLASH_SYMBOL);
    }

    public DefaultUrlPatternMatcher(Map<String, String> customUrlPatternMap) {
        this.customUrlPatternMap = customUrlPatternMap;
    }

    @Override
    public void init(RuntimeProvider provider) {
        requestMappingHandlerMapping = provider.getByType(RequestMappingHandlerMapping.class);
    }

    @Override
    public String getUrlPattern(HttpServletRequest request) {
        CheckUtil.notNull(request, "request null");

        String requestUrl = getRequestUrl(request);
        String urlPattern = customUrlPatternMap.get(requestUrl);

        if (StringUtil.isEmpty(urlPattern)) {
            urlPattern = SecurityResourceUtil.getUrlPattern(requestMappingHandlerMapping, request);
        }

        return urlPattern;
    }

    /**
     * 返回请求URL
     *
     * @param request
     * @return
     */
    protected static String getRequestUrl(HttpServletRequest request) {
        CheckUtil.notNull(request, "request null");
        String returnUrl;

        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();

        if (StringUtil.isEmpty(contextPath)) {
            returnUrl = requestURI;
        } else {
            returnUrl = requestURI.substring(contextPath.length());
        }

        return returnUrl;
    }

}
