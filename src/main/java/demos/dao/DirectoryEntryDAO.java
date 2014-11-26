package demos.dao;

import demos.domain.DirectoryEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DirectoryEntryDAO {

    private static final String FIND_ALL_QUERY = "SELECT * FROM directory_entry";
    private static final RowMapper<DirectoryEntry> ENTRY_ROW_MAPPER = (rs, rowNum) -> new DirectoryEntry(
            rs.getLong("id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("phone"),
            rs.getString("email"),
            rs.getString("statement")
    );

    public static final String SEARCH_QUERY = "SELECT * FROM directory_entry WHERE UPPER(last_name) LIKE '%%%s%%' " +
            "OR UPPER(first_name) LIKE '%%%s%%'";

    private static final String FIND_BY_ID_QUERY = "SELECT * FROM directory_entry WHERE id = %s";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DirectoryEntryDAO(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DirectoryEntry> findWithContainingText(final String text) {
        return jdbcTemplate.query(
                String.format(SEARCH_QUERY, text.toUpperCase(), text.toUpperCase()),
                ENTRY_ROW_MAPPER
        );
    }

    public List<DirectoryEntry> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, ENTRY_ROW_MAPPER);
    }

    public void delete(final long id) {
        throw new UnsupportedOperationException(); // TODO
    }

    public DirectoryEntry findById(final long entryId)
    {
        return jdbcTemplate.queryForObject(
                String.format(FIND_BY_ID_QUERY, entryId),
                ENTRY_ROW_MAPPER
        );
    }
}
