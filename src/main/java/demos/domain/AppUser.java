package demos.domain;

public class AppUser {

    private final Long id;
    private final String username;
    private final String passwordHash;

    public AppUser(final Long id, final String username, final String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
