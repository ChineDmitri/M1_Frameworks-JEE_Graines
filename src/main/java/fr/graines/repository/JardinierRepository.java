package fr.graines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.graines.business.Jardinier;

public interface JardinierRepository extends
                                     JpaRepository<Jardinier, Long> {

}
