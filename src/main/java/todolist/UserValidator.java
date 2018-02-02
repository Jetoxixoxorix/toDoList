package todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.model.User;
import todolist.service.IUserManager;
import todolist.service.UserManager;

@Service
public class UserValidator implements IUserValidator {

    IUserManager userManager;

    @Autowired
    public UserValidator(IUserManager userManager) {
        this.userManager = userManager;
    }

    public void userValidation(User user) throws WrongPasswordException {
        {
            String username = user.getUsername();
            String password = user.getPassword();

            if (userManager.findUser(username) == null)
                throw new NullPointerException();

            if (!userManager.findPassword(username).equals(password))
                throw new WrongPasswordException();

            UserManager.user = user;
        }
    }
}

