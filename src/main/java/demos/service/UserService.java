package demos.service;

import demos.dao.UserDAO;
import demos.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public AppUser getUser(String username) {
        return userDAO.findUserByUsername(username);
    }

}
