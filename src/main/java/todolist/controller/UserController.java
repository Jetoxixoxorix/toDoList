package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import todolist.error.ExistingUsernameException;
import todolist.error.NotMatchingPasswords;
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
    public String registration(@Valid @ModelAttribute("userData") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "registration";

        try {
            userValidator.userValidation(user);
        } catch (ExistingUsernameException e) {
            model.addAttribute("existingUsername", e.getMessage());
            return "registration";
        } catch (NotMatchingPasswords e) {
            model.addAttribute("wrongPassword", e.getMessage());
            return  "registration";
        }

        userManager.userRegistration(user);
        return "completed";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userDataLogin", new User());
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        userManager.logout();
        return "logout";
    }
}
