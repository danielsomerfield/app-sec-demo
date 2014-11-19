package demos.dao;

import demos.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.lang.String.format;

@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;
    private static final String FIND_USER_SQL = "SELECT id, username, password_hash FROM application_user WHERE username = '%s'" +
            "AND password_hash = '%s'";
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
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public AppUser findUserByUsernameAndPassword(final String username, final String password) {
        final String passwordHash = hashPassword(password);
        final List<AppUser> user = jdbcTemplate.query(format(FIND_USER_SQL, username, passwordHash), USER_ROW_MAPPER);
        return user.size() == 0 ? null : user.get(0);
    }

    private String hashPassword(final String password) {
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes("UTF-8"));
            return toHex(m.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to hash password");
        }
    }

    private String toHex(final byte [] bytes) {
        final StringBuffer sb = new StringBuffer();
        for (final byte aByte : bytes) {
            if ((0xff & aByte) < 0x10) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(0xff & aByte));
        }
        return sb.toString();
    }
}
