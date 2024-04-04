package fr.graines.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SachetDTO {

    private Long id;
    private int poidsEnGrammes;
    private float prixEnEuros;
    private TypeDeGraineDTO typeDeGraine;
    private FournisseurDTO fournisseur;

}