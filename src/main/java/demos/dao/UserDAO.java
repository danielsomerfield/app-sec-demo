package demos.dao;

import demos.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final String FIND_USER_SQL = "SELECT id, username, password_hash FROM application_user WHERE username = '%s'";
    private static final RowMapper<AppUser> USER_ROW_MAPPER = new RowMapper<AppUser>() {
        @Override
        public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new AppUser(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password_hash")
            );
        }
    };


    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO: spring is a bit too smart here and doesn't do the search via username and password
    public AppUser findUserByUsername(String username) {
        List<AppUser> user = jdbcTemplate.query(format(FIND_USER_SQL, username), USER_ROW_MAPPER);
        assert user.size() <= 1;
        return user.size() == 1 ? user.get(0) : null;
    }
}
