package demos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping("/service/login")
    @ResponseBody
    public void login(@RequestParam String username, @RequestParam String password) {
        throw new UnsupportedOperationException("TODO: implement call to login service.");
    }
}
