package demos.dao;

import demos.domain.DirectoryEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DirectoryEntryDAO {

    private final JdbcTemplate jdbcTemplate;

    public static final String SEARCH_QUERY = "SELECT * FROM directory_entry WHERE last_name LIKE '%%%s%%' " +
            "OR first_name LIKE '%%%s%%'";

    @Autowired
    public DirectoryEntryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DirectoryEntry> findWithContainingText(String text) {
        return jdbcTemplate.query(
                String.format(SEARCH_QUERY, text, text),
                (rs, rowNum) -> new DirectoryEntry(
                        rs.getString("first_name"),
                        rs.getString("last_name"))
        );
    }
}
