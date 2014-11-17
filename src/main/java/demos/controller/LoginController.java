package demos.controller;

import demos.domain.AppState;
import demos.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/service/login", method= RequestMethod.POST)
    @ResponseBody
    public AppState login(String username, String password){
        userService.findUserByUsernameAndPassword(username, password);
        //Try to authenticate with the username and password
        //If it succeeds, set a session value
        //Return the current app state
        return AppState.builder().withLoggedIn(true).build();
    }

}
