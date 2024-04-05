package fr.graines.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import fr.graines.business.Utilisateur;
import fr.graines.dto.UtilisateurPost;
import fr.graines.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RegistrationController {

    @Autowired
    AuthService authService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping("/registration")
    public String showRegistrationForm(Model model, HttpServletRequest req, HttpSession session) {
        model.addAttribute("user", new UtilisateurPost());
        return authService.isAuth(req.getRequestURI(), "redirect:/", session);
    }

    @PostMapping("/register")
    public String register(UtilisateurPost user, Model model, RedirectAttributes redirectAttributes) {
        LOGGER.info("User registration: usernom={}, password={}", user.getNom(), user.getPassword());

        Utilisateur utilisateurCreated = this.authService.createAccount(user);

        LOGGER.info("User created with : {}", utilisateurCreated);

        if (utilisateurCreated == null) {
            model.addAttribute("error", "Utilisateur avec email-existe déja");
            model.addAttribute("user", new UtilisateurPost());
            return "registration-form";
        }

        redirectAttributes.addFlashAttribute("successMessage",
                                             "Registration avec succès ! S'il vous plait authentifiez vous.");
        return "redirect:/login";
    }
}
