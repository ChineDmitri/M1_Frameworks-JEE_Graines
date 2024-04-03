package fr.graines.business;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Builder
@ToString
@Entity
public class Jardinier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long           id;
    private LocalDateTime  dateDeNaissance;
    @OneToOne(mappedBy = "jardinier")
    private Utilisateur    utilisateur;
    @OneToMany(mappedBy = "jardinier")
    private List<Recette>  recettes;
    @OneToMany(mappedBy = "jardinier")
    private List<Commande> commandes;

}
