package fr.graines.business;

import java.util.List;
import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long               id;
    private String             intitule;
    private String             contenu;
    @ManyToMany
    @JoinTable(
               name = "recette_type_de_graine",
               joinColumns = @JoinColumn(name = "recette_id"),
               inverseJoinColumns = @JoinColumn(name = "type_de_graine_id"))
    private List<TypeDeGraine> typeDeGraines;

}
