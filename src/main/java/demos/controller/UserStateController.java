package demos.controller;

import demos.domain.UserState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import static demos.Constants.USER_STATE_SESSION_ATTRIBUTE;

@Controller
public class UserStateController {

    @RequestMapping(value = "/service/userState", method = RequestMethod.GET)
    @ResponseBody
    public UserState getUserState(final HttpSession session) {
        return new UserState((UserState)session.getAttribute(USER_STATE_SESSION_ATTRIBUTE));
    }

}
