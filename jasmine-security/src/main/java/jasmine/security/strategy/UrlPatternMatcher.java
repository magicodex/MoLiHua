package jasmine.security.strategy;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mh.z
 */
public interface UrlPatternMatcher {

    /**
     * 匹配URL模式并返回
     *
     * @param request
     * @return
     */
    String getUrlPattern(HttpServletRequest request);
}
