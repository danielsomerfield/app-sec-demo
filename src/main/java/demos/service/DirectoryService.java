package demos.service;

import demos.dao.DirectoryEntryDAO;
import demos.domain.DirectoryEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryService {

    private final DirectoryEntryDAO directoryEntryDAO;

    @Autowired
    public DirectoryService(final DirectoryEntryDAO directoryEntryDAO) {
        this.directoryEntryDAO = directoryEntryDAO;
    }

    public List<DirectoryEntry> entriesWithText(final String text) {
        return directoryEntryDAO.findWithContainingText(text);
    }

    public List<DirectoryEntry> allEntries() {
        return directoryEntryDAO.findAll();
    }

    public void deleteEntryById(final long id) {
        directoryEntryDAO.delete(id);
    }

    public DirectoryEntry getEntry(final long entryId)
    {
        return directoryEntryDAO.findById(entryId);
    }
}
