package demos.domain;

public class UserState {

    private boolean loggedIn = false;

    public UserState(final boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public UserState(final UserState userState) {
        this.loggedIn = userState != null && userState.isLoggedIn();
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

}
