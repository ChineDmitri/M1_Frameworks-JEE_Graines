package fr.graines.business;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                id;
    private LocalDateTime       dateHeureDEnvoi;
    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> lignesCommande;
    @ManyToOne
    private Jardinier           jardinier;
    
}
