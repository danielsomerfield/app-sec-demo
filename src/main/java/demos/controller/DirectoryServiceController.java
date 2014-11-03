package demos.controller;

import demos.domain.DirectoryEntry;
import demos.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DirectoryServiceController {

    private DirectoryService directoryService;

    @Autowired
    public DirectoryServiceController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @RequestMapping("/service/search/{query}")
    @ResponseBody
    public List<DirectoryEntry> findEntries(@PathVariable("query") String query) {
        System.out.println(query);
        return directoryService.directoriesWithText(query);
    }
}
