package fr.graines.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import fr.graines.dto.UtilisateurPost;
import fr.graines.service.AuthService;

@Controller
@SessionAttributes("loggedIn")
public class LoginController {

    @Autowired
    AuthService authService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UtilisateurPost());
        return "login-form";
    }

    @PostMapping("/login")
    public String login(UtilisateurPost user, Model model) {
        LOGGER.info("User login: email={}, password={}", user.getEmail(), user.getPassword());
        if (user.getEmail().equals("test") && user.getPassword().equals("test")) {
            model.addAttribute("loggedIn", true);
            return "redirect:/";
        }
        else {
            model.addAttribute("error", "email et/ou mot de passe incorrect(s)");
            model.addAttribute("user", new UtilisateurPost());
            return "login-form";
        }
    }
    
    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/login";
    }

}
