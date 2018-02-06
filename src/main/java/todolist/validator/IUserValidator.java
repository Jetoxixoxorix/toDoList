package todolist.validator;

import todolist.error.ExistingUsernameException;
import todolist.error.NotMatchingPasswords;
import todolist.model.User;

public interface IUserValidator {
    void userValidation(User user) throws ExistingUsernameException, NotMatchingPasswords;
    void checkingUsername(User user) throws ExistingUsernameException;
    void checkingPassword(User user) throws NotMatchingPasswords;
}
