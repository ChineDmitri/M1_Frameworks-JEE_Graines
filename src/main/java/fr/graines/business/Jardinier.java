package fr.graines.business;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Past;
import jdk.jshell.execution.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Jardinier extends Utilisateur {

    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "jardinier")
    private List<Recette> recettes = new ArrayList<>();

    @OneToMany(mappedBy = "jardinier")
    private List<Commande> commandes = new ArrayList<>();
}
