package fr.graines.service;

import fr.graines.dto.SachetDTO;

import java.util.List;

public interface SachetService {

    List<SachetDTO> getAllSachets();

    List<SachetDTO> getSachetsNonCommandes();

    List<SachetDTO> getSachetsOrderByQuantiteCommandeeDesc();
}
