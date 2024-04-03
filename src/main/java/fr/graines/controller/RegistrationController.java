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
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "registration-form";
    }

    @PostMapping("/register")
    public String register(User user, RedirectAttributes redirectAttributes) {
        LOGGER.info("User registration: username={}, password={}", user.getUsername(), user.getPassword());

        redirectAttributes.addFlashAttribute("successMessage", "Registration successful. Please login.");
        return "redirect:/login";
    }
}
