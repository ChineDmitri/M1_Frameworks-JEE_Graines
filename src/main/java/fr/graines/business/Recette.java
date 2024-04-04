package fr.graines.business;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "L'intitulé de la recette ne doit pas être vide")
    private String intitule;
    private String contenu;

    @ManyToMany
    @JoinTable(
            name = "recette_type_graine",
            joinColumns = @JoinColumn(name = "recette_id"),
            inverseJoinColumns = @JoinColumn(name = "type_graine_id")
    )
    private List<TypeDeGraine> typeDeGraines = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "jardinier_id")
    private Jardinier jardinier;
}
