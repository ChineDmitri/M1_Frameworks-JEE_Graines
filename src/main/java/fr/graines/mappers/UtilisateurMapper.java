package fr.graines.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import fr.graines.business.Utilisateur;
import fr.graines.dto.UtilisateurPost;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    @Mapping(source = "adresseEmail",
             target = "email")
    @Mapping(source = "motDePasse",
             target = "password")
    UtilisateurPost utilisateurEntityToUtilisateurPost(Utilisateur utilisateur);

    @Mapping(source = "email",
             target = "adresseEmail")
    @Mapping(source = "password",
             target = "motDePasse")
    Utilisateur utilisateurPostToUtilisateurEntitiy(UtilisateurPost utilisateurPost);
}
