package todolist.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import todolist.model.User;
import todolist.service.IUserManager;
import todolist.service.UserManager;

import java.util.Collection;
import java.util.Map;

@Service
public class MyUserDetailService implements UserDetailsService {

    IUserManager userManager;

    @Autowired
    public MyUserDetailService(IUserManager userManager){
        this.userManager = userManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username)  {
        User user = userManager.findUser(username);
        if(user == null)
            throw new UsernameNotFoundException(username);

        UserManager.user = user;
        return new MyUserPrincipal(user);
    }
}
