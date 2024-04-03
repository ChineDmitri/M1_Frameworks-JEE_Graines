package fr.graines.business;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;
    private String      nom;
    private String      prenom;
    private String      adresseEmail;
    private String      motDePasse;
    @OneToOne
    @JoinColumn(name = "jardinier_id")
    private Jardinier   jardinier;
    @OneToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
    
}
