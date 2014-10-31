package demos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by danielsomerfield on 10/28/14.
 */
@Controller("/injection")
public class SpringInjectionDemoController {

    @RequestMapping(method = RequestMethod.GET)
    public String home()
    {
        return "injection/index";
    }

}

