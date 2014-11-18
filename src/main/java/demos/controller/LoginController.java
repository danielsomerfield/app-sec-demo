package demos.controller;

import demos.domain.AppUser;
import demos.domain.UserState;
import demos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import static demos.Constants.USER_STATE_SESSION_ATTRIBUTE;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/service/login", method= RequestMethod.POST)
    @ResponseBody
    public UserState login(@RequestParam String username, @RequestParam String password, HttpSession session){
        final AppUser user = userService.findUserByUsernameAndPassword(username, password);
        final UserState state = new UserState(user != null);
        session.setAttribute(USER_STATE_SESSION_ATTRIBUTE, state);
        return state;
    }


}
