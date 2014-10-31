package demos.domain;

public class DirectoryEntry {

    private String firstName;
    private String lastName;

    public DirectoryEntry() {
    }

    public DirectoryEntry(String firstName, String lastname) {
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
}
