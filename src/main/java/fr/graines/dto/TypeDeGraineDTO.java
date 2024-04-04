package fr.graines.dto;

import fr.graines.business.Famille;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeDeGraineDTO {
    private Long id;
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
    private Famille famille;
}
