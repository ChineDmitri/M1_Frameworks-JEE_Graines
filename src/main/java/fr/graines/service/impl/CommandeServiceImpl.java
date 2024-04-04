package fr.graines.service.impl;

import fr.graines.business.Commande;
import fr.graines.dto.CommandeDTO;
import fr.graines.dto.LigneCommandeDTO;
import fr.graines.repository.CommandeRepository;
import fr.graines.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public List<CommandeDTO> getAllCommandes() {
        return mapToDtoList(commandeRepository.findAll());
    }

    @Override
    public List<CommandeDTO> getByDateHeureDEnvoiBetween(final LocalDateTime dateHeureDEnvoiStart, final LocalDateTime dateHeureDEnvoiEnd) {
        return mapToDtoList(commandeRepository.findByDateHeureDEnvoiBetween(dateHeureDEnvoiStart, dateHeureDEnvoiEnd));
    }

    @Override
    public List<CommandeDTO> getAllOrderByMontantTotalDesc() {
        return mapToDtoList(commandeRepository.findAllOrderByMontantTotalDesc());
    }

    @Override
    public List<Map<Month, Integer>> countCommandesParMois() {
        return commandeRepository.countCommandesParMois();
    }

    private CommandeDTO mapToDto(Commande commande) {
        final CommandeDTO dto = new CommandeDTO();
        dto.setId(commande.getId());
        dto.setDateHeureDEnvoi(commande.getDateHeureDEnvoi());
        dto.setJardinierId(commande.getJardinier().getId());

        // Mapping ligneCommandes
        final List<LigneCommandeDTO> ligneCommandesDTO = commande.getLigneCommandes().stream()
                .map(ligneCommande -> {
                    LigneCommandeDTO ligneCommandeDTO = new LigneCommandeDTO();
                    ligneCommandeDTO.setId(ligneCommande.getId());
                    // Map other fields as needed
                    return ligneCommandeDTO;
                })
                .collect(Collectors.toList());
        dto.setLigneCommandes(ligneCommandesDTO);

        return dto;
    }

    private List<CommandeDTO> mapToDtoList(List<Commande> commandes) {
        return commandes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
