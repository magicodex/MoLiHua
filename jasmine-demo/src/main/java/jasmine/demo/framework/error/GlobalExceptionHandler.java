package jasmine.demo.framework.error;

import cn.hutool.core.io.resource.ResourceUtil;
import jasmine.core.exception.ApplicationException;
import jasmine.core.util.QJsonUtil;
import jasmine.framework.web.entity.WebResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author mh.z
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response,
                                 Exception error) throws Exception {
        // 记录错误信息
        log.error("request failed", error);

        String servletPath = request.getServletPath();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setCharacterEncoding("UTF-8");

        // "/api/"开头的请求返回 json 格式，其余的返回错误页面
        if (servletPath != null && servletPath.startsWith("/api/")) {
            WebResult result = createErrorWebResult(error);
            String json = QJsonUtil.toJson(result);

            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            writer.write(json);
            writer.flush();
        } else {
            String html = ResourceUtil.readUtf8Str("templates/error/500.html");

            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.write(html);
            writer.flush();
        }
    }

    /**
     * 创建错误结果对象
     *
     * @param error
     * @return
     */
    protected WebResult createErrorWebResult(Exception error) {
        WebResult result = null;

        if (error instanceof ApplicationException) {
            ApplicationException applicationException = (ApplicationException) error;
            result = WebResult.error(null, applicationException.getMessage());

            result.setErrorCode(applicationException.getErrorCode());
            result.setErrorDetail(applicationException.getErrorDetail());
        } else {
            result = WebResult.error(null, error.getMessage());
        }

        return result;
    }

}
