package fr.graines.business;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Sachet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int poidsEnGrammes;
    private float prixEnEuros;

    @ManyToOne
    @JoinColumn(name = "type_graine_id")
    private TypeDeGraine typeDeGraine;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "sachet")
    private List<LigneCommande> ligneCommandes = new ArrayList<>();
}
