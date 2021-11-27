package jasmine.dashboard.application.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "仪表盘")
    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("dashboard/dashboard.html");
    }

    @ApiOperation(value = "重定向到仪表盘")
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public RedirectView index() {
        return new RedirectView("dashboard");
    }

}
