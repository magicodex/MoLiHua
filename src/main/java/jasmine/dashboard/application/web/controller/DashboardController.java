package jasmine.dashboard.application.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author mh.z
 */
@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("dashboard/dashboard.html");
    }

    @RequestMapping("/")
    public RedirectView index() {
        return new RedirectView("dashboard");
    }

}
