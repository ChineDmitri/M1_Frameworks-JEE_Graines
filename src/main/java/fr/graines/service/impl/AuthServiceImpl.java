package fr.graines.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.graines.business.Utilisateur;
import fr.graines.dto.UtilisateurPost;
import fr.graines.mappers.UtilisateurMapper;
import fr.graines.repository.UtilisateurRepository;
import fr.graines.service.AuthService;

@Service
public class AuthServiceImpl implements
                             AuthService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Override
    public Utilisateur authentification(UtilisateurPost utilisateurPost) {

        Utilisateur utilisateur = this.utilisateurRepository.findByAdresseEmailAndMotDePasse(utilisateurPost.getEmail(),
                                                                                             utilisateurPost.getPassword());

        if (utilisateur != null)
            return utilisateur;

        return null;
    }

    @Override
    public String isAuth(String target) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Utilisateur createAccount(UtilisateurPost utilisateur) {
        // TODO Auto-generated method stub
        Utilisateur utilisateurEntity = utilisateurMapper.utilisateurPostToUtilisateurEntitiy(utilisateur);

        utilisateurRepository.save(utilisateurEntity);
        
        if (utilisateurEntity.getId() != null) {
            return utilisateurEntity;
        }
        return null;
    }



}
