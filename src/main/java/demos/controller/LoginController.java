package demos.controller;

import demos.Constants;
import demos.domain.AppUser;
import demos.domain.UserState;
import demos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/service/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserState> login(@RequestParam final String username,
                                           @RequestParam final String password,
                                           HttpSession session) {

        final AppUser user = userService.findUserByUsernameAndPassword(username, password);
        return new ResponseEntity<>(
                storeAppState(new UserState(user != null), session),
                user != null ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }

    private UserState storeAppState(final UserState userState, final HttpSession session) {
        session.setAttribute(Constants.USER_STATE_SESSION_ATTRIBUTE, userState);
        return userState;
    }

    @RequestMapping(value = "/service/logout", method = RequestMethod.POST)
    @ResponseBody
    public UserState logout(HttpSession session) {
        return storeAppState(new UserState(false), session);
    }

}
