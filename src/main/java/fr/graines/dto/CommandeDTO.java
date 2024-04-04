package fr.graines.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommandeDTO {

    private Long id;
    private LocalDateTime dateHeureDEnvoi;

    @NotNull
    @Size(min = 1, message = "La commande doit avoir au moins une ligne de commande")
    private List<LigneCommandeDTO> ligneCommandes;

    private Long jardinierId;
}
