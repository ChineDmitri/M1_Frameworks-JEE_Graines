package fr.graines.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import fr.graines.business.Utilisateur;
import fr.graines.dto.UtilisateurPost;
import fr.graines.mappers.UtilisateurMapper;
import fr.graines.repository.UtilisateurRepository;
import fr.graines.service.AuthService;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements
                             AuthService {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    private static final String LOGGIN_ROUTE = "redirect:/login";

    @Override
    public Utilisateur authentification(UtilisateurPost utilisateurPost) {

        Utilisateur utilisateur = this.utilisateurRepository.findByAdresseEmailAndMotDePasse(utilisateurPost.getEmail(),
                                                                                             utilisateurPost.getPassword());

        if (utilisateur != null)
            return utilisateur;

        return null;
    }

    @Override
    public String isAuth(String from, String to, HttpSession session) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        
        if (loggedIn != null && loggedIn) {
            System.err.println("if2");
            return to;
        }
        
        if (from.equals("/registration")) {
            System.err.println("if1");
            return "registration-form";
        }

        return AuthServiceImpl.LOGGIN_ROUTE;
    }

    @Override
    public Utilisateur createAccount(UtilisateurPost utilisateur) {
        Utilisateur utilisateurEntity = utilisateurMapper.utilisateurPostToUtilisateurEntitiy(utilisateur);

        try {
            utilisateurRepository.save(utilisateurEntity);
        } catch (DataIntegrityViolationException e) {
            return null;
        }

        if (utilisateurEntity.getId() != null) {
            return utilisateurEntity;
        }
        return null;
    }



}
