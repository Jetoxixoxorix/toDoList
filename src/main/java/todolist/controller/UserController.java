package todolist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import todolist.model.User;
import todolist.service.IUserManager;

import javax.validation.Valid;

@Controller
@RequestMapping
public class UserController {

    IUserManager userManager;

    @Autowired
    public UserController(IUserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userData", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userData") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "registration";

        userManager.userRegistration(user);
        return "completed";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userDataLogin", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("userDataLogin") User user) {
        return userManager.loginValidation(user);
    }

    @GetMapping("/logout")
    public String logout() {
        userManager.logout();
        return "logout";
    }
}
