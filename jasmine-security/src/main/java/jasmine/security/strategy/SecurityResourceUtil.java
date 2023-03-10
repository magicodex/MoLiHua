package jasmine.security.strategy;

import jasmine.core.util.CheckUtil;
import jasmine.security.rbac.dto.SecResourceBaseInfoDTO;
import jasmine.security.rbac.model.SecResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.ServletRequestPathUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mh.z
 */
public class SecurityResourceUtil {
    private static final Logger logger = LoggerFactory.getLogger(SecurityResourceUtil.class);

    /**
     * 返回 URL 模式
     *
     * @param handlerMapping
     * @return
     */
    public static String getUrlPattern(HandlerMapping handlerMapping, HttpServletRequest request) {
        CheckUtil.notNull(handlerMapping, "handlerMapping null");

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest realRequest = ((ServletRequestAttributes) requestAttributes).getRequest();

        // 计算请求的最佳匹配模式
        try {
            ServletRequestPathUtils.parseAndCache(realRequest);
            handlerMapping.getHandler(realRequest);
        } catch (Exception e) {
            logger.warn("HandlerMapping.getHandler failed", e);
        }

        // 获取 url 模式
        String urlPattern = (String) realRequest.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        return urlPattern;
    }

    /**
     * 转换成 SecResourceBaseInfoDTO 对象
     *
     * @param resource
     * @return
     */
    public static SecResourceBaseInfoDTO toResourceBaseInfoDTO(SecResource resource) {
        if (resource == null) {
            return null;
        }

        SecResourceBaseInfoDTO resourceBaseInfo = new SecResourceBaseInfoDTO();
        resourceBaseInfo.setResourceId(resource.getId());
        resourceBaseInfo.setResourceType(resource.getResourceType());
        resourceBaseInfo.setAccessMethod(resource.getAccessMethod());
        resourceBaseInfo.setAccessPolicy(resource.getAccessPolicy());
        resourceBaseInfo.setResourcePath(resource.getResourcePath());
        resourceBaseInfo.setFrozenFlag(resource.getFrozenFlag());

        return resourceBaseInfo;
    }

}
