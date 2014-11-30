package demos.dao;

import demos.domain.AppUser;
import demos.service.CryptoService;
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
    private static final String FIND_USER_SQL = "SELECT id, username, password_hash FROM application_user WHERE username = '%s'" +
            "AND password_hash = '%s'";

    private CryptoService cryptoService;

    private static final RowMapper<AppUser> USER_ROW_MAPPER = new RowMapper<AppUser>() {
        @Override
        public AppUser mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            return new AppUser(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password_hash")
            );
        }
    };


    @Autowired
    public UserDAO(final JdbcTemplate jdbcTemplate, final CryptoService cryptoService) {
        this.jdbcTemplate = jdbcTemplate;
        this.cryptoService = cryptoService;
    }


    public AppUser findUserByUsernameAndPassword(final String username, final String password) {
        final String passwordHash = cryptoService.hashPassword(password);
        final List<AppUser> user = jdbcTemplate.query(format(FIND_USER_SQL, username, passwordHash), USER_ROW_MAPPER);
        return user.size() == 0 ? null : user.get(0);
    }

}
