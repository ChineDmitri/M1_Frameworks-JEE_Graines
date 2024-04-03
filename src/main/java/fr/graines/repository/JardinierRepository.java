package fr.graines.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import fr.graines.business.Jardinier;

public interface JardinierRepository extends
                                     JpaRepository<Jardinier, Long> {

     @Query("SELECT j FROM Jardinier j LEFT JOIN j.commandes c GROUP BY j ORDER BY COUNT(c) DESC")
     List<Jardinier> findAllOrderByCountCommandesDesc();
     
}
