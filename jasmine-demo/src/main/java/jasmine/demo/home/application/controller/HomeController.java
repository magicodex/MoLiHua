package jasmine.demo.home.application.controller;

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
@Api(tags = "主页")
@RestController
public class HomeController {

    @ApiOperation(value = "主页")
    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home/home.html");
    }

    @ApiOperation(value = "重定向到主页")
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public RedirectView index() {
        return new RedirectView("home");
    }

}
