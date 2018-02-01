package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.model.User;
import todolist.repository.UserRepository;

@Service
public class UserManager implements IUserManager {

    public static User user;

    @Autowired
    public UserRepository userRepository;

    public void userRegistration(User user){
        userRepository.save(user);
    }

    public String loginValidation(User user){
        String username = user.getUsername();
        String password = user.getPassword();

        if(userRepository.findUserByUsername(username) != null){
            if(userRepository.findUserByUsername(username).getPassword().equals(password)){
                this.user = user;
                return "logincompleted";
            }
        }
        return "login";
    }

    public Long getUserId(){
        return userRepository.findUserByUsername(user.getUsername()).getUserId();
    }

    public void logout(){
        user = null;
    }
}
