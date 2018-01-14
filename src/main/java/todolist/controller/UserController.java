package todolist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import todolist.model.User;
import todolist.repository.UserRepository;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

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
    public String login(@ModelAttribute("userDataLogin") User user, String username, String password){
        //user.setLogin(username);
        //user.setPassword(password);
        return "login";
    }

    @GetMapping("/users")
    public @ResponseBody Iterable<User> getAllUsers() {
            return userRepository.findAll();
    }
}
