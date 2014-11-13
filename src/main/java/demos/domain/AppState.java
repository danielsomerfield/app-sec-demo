package demos.domain;

import demos.controller.AppStateController;

public class AppState {
    private boolean loggedIn = false;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public static AppStateBuilder builder() {
        return new AppStateBuilder();
    }

    public static class AppStateBuilder {
        private AppState appState = new AppState();

        public AppStateBuilder withLoggedIn(boolean loggedIn){
            appState.setLoggedIn(loggedIn);
            return this;
        }

        public AppState build(){
            return appState;
        }
    }
}
