package demos.service;

import demos.dao.UserDAO;
import demos.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public AppUser findUserByUsernameAndPassword(final String username, final String password) {
        return userDAO.findUserByUsernameAndPassword(username, password);
    }
}
