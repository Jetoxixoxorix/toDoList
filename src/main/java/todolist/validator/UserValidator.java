package todolist.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.error.ExistingUsernameException;
import todolist.model.User;
import todolist.service.IUserManager;

@Service
public class UserValidator implements IUserValidator {

    IUserManager userManager;

    @Autowired
    public UserValidator(IUserManager userManager) {
        this.userManager = userManager;
    }

    public void userValidation(User user) throws ExistingUsernameException {
        {
            String username = user.getUsername();

            if (userManager.findUser(username) != null)
                throw new ExistingUsernameException();
        }
    }
}
