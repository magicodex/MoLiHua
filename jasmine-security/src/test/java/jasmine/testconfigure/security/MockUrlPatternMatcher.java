package jasmine.testconfigure.security;

import jasmine.core.util.CheckUtil;
import jasmine.security.strategy.UrlPatternMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mh.z
 */
public class MockUrlPatternMatcher implements UrlPatternMatcher {

    @Override
    public String getUrlPattern(HttpServletRequest request) {
        CheckUtil.notNull(request, "request null");

        return request.getRequestURI();
    }

}
