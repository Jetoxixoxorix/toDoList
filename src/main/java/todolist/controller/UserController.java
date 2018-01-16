package todolist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import todolist.model.User;
import todolist.repository.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping
@Service
public class UserController {

    static User user;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userData", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userData") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "registration";

        userRepository.save(user);
        return "completed";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userDataLogin", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userDataLogin") User user){
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

    @GetMapping("/eloszka")
    public String eloszka(@ModelAttribute("userDataLogin") User user){
        return this.user.toString() + userRepository.findUserByUsername(this.user.getUsername()).getUserId();
    }

    @GetMapping("/users")
    public @ResponseBody Iterable<User> getAllUsers() {
            return userRepository.findAll();
    }
}
