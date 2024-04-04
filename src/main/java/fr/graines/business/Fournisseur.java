package fr.graines.business;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fournisseur extends Utilisateur{

    @Pattern(regexp = "^(06|07)\\d{8}$", message = "Le numéro de téléphone du fournisseur doit débuter par 06 ou 07")
    private String numeroDePortable;

    @OneToMany(mappedBy = "fournisseur")
    private List<Sachet> sachets;

}
