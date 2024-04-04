package fr.graines.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import fr.graines.business.Jardinier;
import java.time.temporal.TemporalUnit;
import org.springframework.data.repository.query.Param;

public interface JardinierRepository extends JpaRepository<Jardinier, Long> {

     @Query("SELECT j FROM Jardinier j LEFT JOIN j.commandes c GROUP BY j ORDER BY COUNT(c) DESC")
     List<Jardinier> findAllOrderByCountCommandesDesc();

     @Query("SELECT DISTINCT j FROM Jardinier j JOIN j.commandes c JOIN c.ligneCommandes lc JOIN lc.sachet s JOIN s.typeDeGraine tg WHERE tg.nom = :nomGraine")
     List<Jardinier> findByTypeDeGraineNom(@Param("nomGraine") String nomGraine);

     @Query("SELECT j FROM Jardinier j WHERE j.dateNaissance <= :dateLimite")
     List<Jardinier> findJardiniersPlusDe60Ans(@Param("dateLimite") LocalDate dateLimite);

}

