package fr.graines.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FournisseurDTO {

    private Long id;
    private String motDePasse;
    private String adresseEmail;
    private String nom;
    private String prenom;
    @Pattern(regexp = "^(06|07)\\d{8}$", message = "Le numéro de téléphone du fournisseur doit débuter par 06 ou 07")
    private String numeroDePortable;
}
