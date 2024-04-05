package fr.graines.service;

import fr.graines.business.Utilisateur;
import fr.graines.dto.UtilisateurPost;
import jakarta.servlet.http.HttpSession;

public interface AuthService {
    
    public Utilisateur authentification(UtilisateurPost utilisateurPost);
    
    public String isAuth(String from, String to, HttpSession session);
    
    public Utilisateur createAccount(UtilisateurPost utilisateur);
}
