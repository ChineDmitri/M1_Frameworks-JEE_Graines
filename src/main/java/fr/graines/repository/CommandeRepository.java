package fr.graines.repository;

import fr.graines.business.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByDateHeureDEnvoiBetween(final LocalDateTime dateHeureDEnvoiStart, final LocalDateTime dateHeureDEnvoiEnd);

    @Query("SELECT c FROM Commande c LEFT JOIN c.ligneCommandes lc GROUP BY c.id ORDER BY SUM(lc.quantite * lc.sachet.prixEnEuros) DESC")
    List<Commande> findAllOrderByMontantTotalDesc();

    @Query("SELECT MONTH(c.dateHeureDEnvoi) AS mois, COUNT(c) AS nombreCommandes FROM Commande c GROUP BY MONTH(c.dateHeureDEnvoi)")
    List<Map<Month, Integer>> countCommandesParMois();


}
