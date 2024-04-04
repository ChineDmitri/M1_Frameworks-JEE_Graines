package fr.graines.service;

import fr.graines.business.Utilisateur;
import fr.graines.dto.UtilisateurPost;

public interface AuthService {
    
    public Utilisateur authentification(UtilisateurPost utilisateurPost);
    
    public String isAuth(String target);
    
    public Utilisateur createAccount(UtilisateurPost utilisateur);
}
