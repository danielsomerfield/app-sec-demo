package demos.domain;

public class DirectoryEntry {

    private String firstName;
    private String lastName;
    private long id;

    public DirectoryEntry() {
    }

    public DirectoryEntry(final long id, final String firstName, final String lastname) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
