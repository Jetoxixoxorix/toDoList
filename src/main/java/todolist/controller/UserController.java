package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import todolist.validator.IUserValidator;
import todolist.model.User;
import todolist.service.IUserManager;

import javax.validation.Valid;

@Controller
@RequestMapping
public class UserController {

    IUserManager userManager;
    IUserValidator userValidator;

    @Autowired
    public UserController(IUserManager userManager, IUserValidator userValidator) {
        this.userManager = userManager;
        this.userValidator = userValidator;
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

/*    @PostMapping("/login")
    public String login(@ModelAttribute("userDataLogin") User user) {
        try {
            userValidator.userValidation(user);
        } catch (NullPointerException e) {
            return "login";
        } catch (WrongPasswordException e){
            return "login";
        }

        return "logincompleted";
    }*/

    @GetMapping("/logout")
    public String logout() {
        userManager.logout();
        return "logout";
    }
}
