package demos.controller;

import demos.domain.AppState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppStateController {

    @RequestMapping(value = "/service/appState", method = RequestMethod.GET)
    @ResponseBody
    public AppState getAppState(HttpServletRequest request) {
        return AppState.builder()
                .withLoggedIn(request.getUserPrincipal() != null)
                .build();
    }

}
