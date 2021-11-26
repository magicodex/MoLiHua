package jasmine.authentication.application.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mh.z
 */
@RestController
public class AuthenticationController {

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login/login.html");
    }

}
