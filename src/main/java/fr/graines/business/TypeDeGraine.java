package fr.graines.business;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class TypeDeGraine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String nom;
    private String description;
    private int    semaineDePlantationMin;
    private int    semaineDePlantationMax;
    private float  espacementEntrePiedsEnCentimetres;
    private float  espacementEntreLignesEnCentimetres;
    private String conseils;

}
