package fr.graines.repository;

import fr.graines.business.Sachet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SachetRepository extends JpaRepository<Sachet, Long> {
    /**
     * @return Une liste des sachets qui n'ont jamais été commandés
     */
    @Query("SELECT s FROM Sachet s WHERE s NOT IN (SELECT lc.sachet FROM LigneCommande lc)")
    List<Sachet> findSachetsNonCommandes();

    /**
     * @return Une liste des sachets triés par ordre décroissant de la quantité commandée
     */
    List<Sachet> findAllOrderByQuantiteCommandeeDesc();
}