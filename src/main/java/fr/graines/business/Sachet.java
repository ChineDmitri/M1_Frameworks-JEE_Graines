package fr.graines.business;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Sachet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long         id;
    private int          poidsEnGrammes;
    private float        prixEnEuros;
    @ManyToOne
    private TypeDeGraine typeDeGraine;
    @ManyToOne
    private Famille      famille;

}
