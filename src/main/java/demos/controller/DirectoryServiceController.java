package demos.controller;

import demos.domain.DirectoryEntry;
import demos.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return directoryService.entriesWithText(query);
    }

    @RequestMapping("/service/entries/")
    @ResponseBody
    public List<DirectoryEntry> allEntries() {
        return directoryService.allEntries();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/service/entries/{id}", method = RequestMethod.DELETE)
    public void deleteEntry(@PathVariable long id) {
        directoryService.deleteEntryById(id);
    }
}
