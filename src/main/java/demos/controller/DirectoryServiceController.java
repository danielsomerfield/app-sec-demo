package demos.controller;

import demos.domain.DirectoryEntry;
import demos.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DirectoryServiceController {

    private DirectoryService directoryService;

    @Autowired
    public DirectoryServiceController(final DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @RequestMapping("/service/search/{query}")
    @ResponseBody
    public List<DirectoryEntry> findEntries(@PathVariable("query") final String query) {
        return directoryService.entriesWithText(query);
    }

    @RequestMapping("/service/entries/")
    @ResponseBody
    public List<DirectoryEntry> allEntries() {
        return directoryService.allEntries();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/service/entries/{id}", method = RequestMethod.DELETE)
    public void deleteEntry(@PathVariable final long id) {
        directoryService.deleteEntryById(id);
    }

    @RequestMapping(value="/entry", method=RequestMethod.GET)
    public ModelAndView entry(@RequestParam final String entryId) {
        final ModelAndView view = new ModelAndView("injection/entry-dialog");
        try
        {
            view.addObject("entry", directoryService.getEntry(Long.parseLong(entryId)));
        }
        catch (final Exception e) {
            view.addObject("error", String.format("Failed to find entry with id %s", entryId));
        }
        return view;
    }
}
