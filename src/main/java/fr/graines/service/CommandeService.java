package fr.graines.service;

import fr.graines.dto.CommandeDTO;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

public interface CommandeService {

    List<CommandeDTO> getAllCommandes();

    List<CommandeDTO> getByDateHeureDEnvoiBetween(final LocalDateTime dateHeureDEnvoiStart, final LocalDateTime dateHeureDEnvoiEnd);

    List<CommandeDTO> getAllOrderByMontantTotalDesc();

    List<Map<Month, Integer>> countCommandesParMois();

}
