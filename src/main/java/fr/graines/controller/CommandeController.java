package fr.graines.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class CommandeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandeController.class);

    @GetMapping("/commande")
    public String showCommandePage(HttpSession session) {
        LOGGER.info("Showing commande page");
        if (session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn")) {
            LOGGER.info("Showing commande page");
            return "commande-page";
        } else {
            return "redirect:/login";
        }
    }
}
