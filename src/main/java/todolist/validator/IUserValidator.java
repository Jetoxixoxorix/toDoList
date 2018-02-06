package todolist.validator;

import todolist.error.ExistingUsernameException;
import todolist.model.User;

public interface IUserValidator {
    void userValidation(User user) throws ExistingUsernameException;
}
