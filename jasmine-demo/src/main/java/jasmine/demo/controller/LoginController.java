package jasmine.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jasmine.core.util.NewUtil;
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
@Tag(name = "登录")
@RestController
public class LoginController {
    private static final String SPRING_SECURITY_LAST_EXCEPTION = "SPRING_SECURITY_LAST_EXCEPTION";

    @Operation(summary = "登录认证")
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = NewUtil.map();

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
