package demos.service;

import demos.dao.DirectoryEntryDAO;
import demos.domain.DirectoryEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryService {

    private DirectoryEntryDAO directoryEntryDAO;

    @Autowired
    public DirectoryService(DirectoryEntryDAO directoryEntryDAO) {
        this.directoryEntryDAO = directoryEntryDAO;
    }

    public List<DirectoryEntry> directoriesWithText(String text) {
        return directoryEntryDAO.findWithContainingText(text);
    }
}
