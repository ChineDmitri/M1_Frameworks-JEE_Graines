package fr.graines.repository;

import fr.graines.business.TypeDeGraine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeDeGraineRepository extends JpaRepository<TypeDeGraine, Long> {

    @Query("SELECT t FROM TypeDeGraine t WHERE :currentWeek BETWEEN t.semaineDePlantationMin AND t.semaineDePlantationMax")
    List<TypeDeGraine> findTypesDeGrainePourPlantation(@Param("currentWeek") int currentWeek);

    @Query("SELECT t FROM TypeDeGraine t LEFT JOIN t.recettes r GROUP BY t.id ORDER BY COUNT(r) DESC")
    List<TypeDeGraine> findTypesDeGraineTrieSurNombreDeRecettes();
}
