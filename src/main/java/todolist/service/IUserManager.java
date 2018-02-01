package todolist.service;

import todolist.model.User;

public interface IUserManager {
    void userRegistration(User user);
    Long getUserId();
    void logout();
    User findUser(String username);
    String findPassword(String username);
}
