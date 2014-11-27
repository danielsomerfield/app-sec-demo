package demos.domain;

public class DirectoryEntry {

    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String statement;

    public DirectoryEntry(final long id, final String firstName, final String lastName, final String phoneNumber,
                          final String email, final String statement) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.statement = statement;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public String getStatement()
    {
        return statement;
    }

    public void setStatement(final String statement)
    {
        this.statement = statement;
    }
}
