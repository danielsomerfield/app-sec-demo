package demos.controller;

import demos.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static demos.service.LoginService.LoginResult;

//@Controller
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/service/login")
    @ResponseBody
    public void login(@RequestParam String username, @RequestParam String password) {
        LoginResult result = loginService.login(username, password);
    }

}
