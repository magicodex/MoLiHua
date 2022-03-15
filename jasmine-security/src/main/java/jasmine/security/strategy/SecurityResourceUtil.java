package jasmine.security.strategy;

import jasmine.core.util.QCheckUtil;
import jasmine.security.rbac.dto.SecResourceBaseInfoDTO;
import jasmine.security.rbac.model.SecResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;

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
        QCheckUtil.notNull(handlerMapping, "handlerMapping null");

        // 计算请求的最佳匹配模式
        try {
            handlerMapping.getHandler(request);
        } catch (Exception e) {
            logger.warn("HandlerMapping.getHandler failed", e);
        }

        // 获取 url 模式
        String urlPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

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
