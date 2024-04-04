package fr.graines.service;

import fr.graines.dto.JardinierDTO;

import java.time.LocalDate;
import java.util.List;

public interface JardinierService {

    List<JardinierDTO> getAllOrderByCountCommandesDesc();

    List<JardinierDTO> getByTypeDeGraineNom(final String nomGraine);

    List<JardinierDTO> getJardiniersPlusDe60Ans(final LocalDate dateLimite);
}
