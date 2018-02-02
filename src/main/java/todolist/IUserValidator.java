package todolist;

import todolist.model.User;

public interface IUserValidator {
    void userValidation(User user) throws WrongPasswordException;
}
