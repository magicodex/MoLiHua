package jasmine.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author mh.z
 */
@Tag(name = "主页")
@RestController
public class HomeController {

    @Operation(summary = "主页")
    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("home/home.html");
    }

    @Operation(summary = "重定向到主页")
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public RedirectView index() {
        return new RedirectView("home");
    }

}
