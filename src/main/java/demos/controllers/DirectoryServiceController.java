package demos.controllers;

import demos.domain.DirectoryEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class DirectoryServiceController {

    @RequestMapping("/service/search/{query}")
    @ResponseBody
    public List<DirectoryEntry> findEntries(@PathVariable("query") String query)
    {
        return Arrays.asList(new DirectoryEntry("firstname1", "lastname1"), new DirectoryEntry("firstname2", "lastname2"));
    }
}
