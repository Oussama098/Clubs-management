package com.ous.web.Controllers;

import com.ous.web.DTO.RegistrationDTO;
import com.ous.web.Repositories.UserRepository;
import com.ous.web.Services.UserService;
import com.ous.web.models.UserEntity;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new UserEntity());
        return "login";
    }

    @GetMapping(path = "/register")
    public String registerPage(Model model) {
        RegistrationDTO user = new RegistrationDTO();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(path = "/register/save")
    public String registerPage(@Valid @ModelAttribute("user") RegistrationDTO user, Model model, BindingResult bindingResult) {
        UserEntity existingUserEmail = this.userService.getUserByEmail(user.getEmail());
        if(existingUserEmail != null) {
            return "redirect:/register?fail=email";
        }
        UserEntity existingUserUsername = this.userService.getUserByUsername(user.getUsername());
        if(existingUserUsername !=null){
            return "redirect:/register?fail= username";
        }
        if (bindingResult.hasErrors()) {
            return "register";
        }
        this.userService.saveUser(user);
        return "redirect:/login?success";
    }
}
