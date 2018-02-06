package todolist.validator;

import todolist.error.WrongPasswordException;
import todolist.model.User;

public interface IUserValidator {
    void userValidation(User user) throws WrongPasswordException;
}
