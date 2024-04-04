package fr.graines.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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
@Entity
public class TypeDeGraine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String nom;

    @Size(min = 40, message = "La description sur le type de graine doit contenir au minimum 40 caractères")
    private String description;

    @Min(value = 1, message = "Les semaines de plantation doivent être comprises entre 1 et 52")
    @Max(value = 52, message = "Les semaines de plantation doivent être comprises entre 1 et 52")
    private int semaineDePlantationMin;

    @Min(value = 1, message = "Les semaines de plantation doivent être comprises entre 1 et 52")
    @Max(value = 52, message = "Les semaines de plantation doivent être comprises entre 1 et 52")
    private int semaineDePlantationMax;
    private float espacementEntrePiedsEnCentimetres;
    private float espacementEntreLignesEnCentimetres;
    private String conseils;
    @OneToOne
    @JoinColumn(name = "famille_id")
    private Famille famille;

    @ManyToMany(mappedBy = "typeDeGraines")
    private List<Recette> recettes = new ArrayList<>();

    @OneToMany(mappedBy = "typeDeGraine")
    private List<Sachet> sachets = new ArrayList<>();

}
