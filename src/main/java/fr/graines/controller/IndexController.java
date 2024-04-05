package fr.graines.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String showIndexPage(HttpSession session) {
        LOGGER.info("Showing index page");
        if (session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn")) {
            LOGGER.info("Showing index page");
            return "index-page";
        } else {
            return "redirect:/login";
        }
    }
}
