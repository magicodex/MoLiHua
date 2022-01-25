package jasmine.demo.authentication.application.web.controller;

import jasmine.framework.util.QNewUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author mh.z
 */
@Api(tags = "认证")
@RestController
public class AuthenticationController {
    private static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

    @ApiOperation(value = "登录认证")
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = QNewUtil.map();

        // 根据指定的请求属性判断是否认证失败
        Object exception = request.getAttribute(SPRING_SECURITY_LAST_EXCEPTION);
        if (exception instanceof BadCredentialsException) {
            BadCredentialsException failException = (BadCredentialsException) exception;

            String message = failException.getMessage();
            model.put("errorMessage", message);
        }

        return new ModelAndView("login/login.html", model);
    }

}
