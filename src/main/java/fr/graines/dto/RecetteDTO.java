package fr.graines.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecetteDTO {

    private Long id;

    @NotNull(message = "L'intitulé de la recette ne doit pas être vide")
    private String intitule;

    private String contenu;

    private List<TypeDeGraineDTO> typeDeGraines;

    private Long jardinierId;
}
