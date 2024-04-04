package fr.graines.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JardinierDTO {

    private Long id;
    private String motDePasse;
    private String adresseEmail;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private List<RecetteDTO> recettes;
    private List<CommandeDTO> commandes;
}
