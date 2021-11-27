package jasmine.dashboard.application.web.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author mh.z
 */
@Api(tags = "仪表盘")
@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("dashboard/dashboard.html");
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public RedirectView index() {
        return new RedirectView("dashboard");
    }

}
