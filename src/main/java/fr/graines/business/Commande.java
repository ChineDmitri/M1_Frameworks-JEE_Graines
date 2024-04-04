package fr.graines.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateHeureDEnvoi;

    @OneToMany(mappedBy = "commande")
    @Size(min = 1, message = "La commande doit avoir au moins une ligne de commande")
    private List<LigneCommande> ligneCommandes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "jardinier_id")
    private Jardinier jardinier;

}
