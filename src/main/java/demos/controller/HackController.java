package demos.controller;

import demos.service.HackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HackController {

    private final HackService hackService;

    @Autowired
    public HackController(final HackService hackService) {
        this.hackService = hackService;
    }

    @RequestMapping(value = "/hackHash/{hash}", method = RequestMethod.GET)
    @ResponseBody
    public HackService.HackResult hackHash(@PathVariable final String hash) {
        return hackService.reverseHash(hash);
    }
}
