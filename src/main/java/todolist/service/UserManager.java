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

    public User findUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    public String findPassword(String username){
        return findUser(username).getPassword();
    }

    public Long getUserId(){
        return findUser(user.getUsername()).getUserId();
    }

    public void logout(){
        user = null;
    }
}
