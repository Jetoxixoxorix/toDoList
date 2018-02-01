package todolist.service;

import todolist.model.User;

public interface IUserManager {
    void userRegistration(User user);
    String loginValidation(User user);
    Long getUserId();
    void logout();
}
