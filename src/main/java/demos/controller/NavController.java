    package demos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by danielsomerfield on 10/29/14.
 */
@Controller
public class NavController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home()
    {
        return "index";
    }

    @RequestMapping(value="/injection", method = RequestMethod.GET)
    public String injectionHome()
    {
        return "injection/index";
    }
}
