package demos.domain;

public class AppUser {

    private Long id;
    private String username;
    private String passwordHash;

    public AppUser(Long id, String username, String passwordHash) {
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
