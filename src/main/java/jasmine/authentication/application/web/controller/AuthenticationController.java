package jasmine.authentication.application.web.controller;

import jasmine.common.util.Q;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author mh.z
 */
@RestController
public class AuthenticationController {

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = Q.map();

        Object exception = request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        if (exception instanceof BadCredentialsException) {
            BadCredentialsException failException = (BadCredentialsException) exception;

            String message = failException.getMessage();
            model.put("errorMessage", message);
        }

        return new ModelAndView("login/login.html", model);
    }

}
