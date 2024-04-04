package fr.graines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.graines.business.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    
    Utilisateur findByAdresseEmailAndMotDePasse(String adresseEmail, String motDePasse);
    
}
