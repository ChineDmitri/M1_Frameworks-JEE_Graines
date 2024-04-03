package fr.graines.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import fr.graines.dto.User;

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String showLoginForm(Model model) {
//        if (model.containsAttribute("successMessage")) {
//            model.addAttribute("successMessage", model.getAttribute("successMessage"));
//        }
//        model.addAttribute("user", new User()); 
//        
//        return "login";
        model.addAttribute("user", new User());
//
        return "login-form";
    }
    
    @PostMapping("/auth")
    public String login(User user, RedirectAttributes redirectAttributes) {
        // LOGGER.info("Username entered: {}", username);
        // LOGGER.info("Password entered: {}", password);
        LOGGER.info("User registration: username={}, password={}", user.getUsername(), user.getPassword());
        
        return "redirect:/index";
    }

}
