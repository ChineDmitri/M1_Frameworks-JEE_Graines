package fr.graines.service.impl;

import fr.graines.business.Jardinier;
import fr.graines.dto.CommandeDTO;
import fr.graines.dto.JardinierDTO;
import fr.graines.dto.RecetteDTO;
import fr.graines.repository.JardinierRepository;
import fr.graines.service.JardinierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JardinierServiceImpl implements JardinierService {

    private JardinierRepository jardinierRepository;

    @Autowired
    public JardinierServiceImpl(JardinierRepository jardinierRepository) {
        this.jardinierRepository = jardinierRepository;
    }

    @Override
    public List<JardinierDTO> getAllOrderByCountCommandesDesc() {
        return mapToDtoList(jardinierRepository.findAll());
    }

    @Override
    public List<JardinierDTO> getByTypeDeGraineNom(String nomGraine) {
        return mapToDtoList(jardinierRepository.findByTypeDeGraineNom(nomGraine));
    }

    @Override
    public List<JardinierDTO> getJardiniersPlusDe60Ans(LocalDate dateLimite) {
        return mapToDtoList(jardinierRepository.findJardiniersPlusDe60Ans(LocalDate.of(1963,12,31)));
    }

    private JardinierDTO mapToDto(Jardinier jardinier) {
        JardinierDTO dto = new JardinierDTO();
        dto.setId(jardinier.getId());
        dto.setMotDePasse(jardinier.getMotDePasse());
        dto.setAdresseEmail(jardinier.getAdresseEmail());
        dto.setNom(jardinier.getNom());
        dto.setPrenom(jardinier.getPrenom());
        dto.setDateNaissance(jardinier.getDateNaissance());

        // Mapping recettes
        List<RecetteDTO> recettesDTO = jardinier.getRecettes().stream()
                .map(recette -> {
                    RecetteDTO recetteDTO = new RecetteDTO();
                    recetteDTO.setId(recette.getId());
                    recetteDTO.setIntitule(recette.getIntitule());
                    recetteDTO.setContenu(recette.getContenu());
                    // You may need to map typeDeGraines as well
                    return recetteDTO;
                })
                .collect(Collectors.toList());
        dto.setRecettes(recettesDTO);

        // Mapping commandes
        List<CommandeDTO> commandesDTO = jardinier.getCommandes().stream()
                .map(commande -> {
                    CommandeDTO commandeDTO = new CommandeDTO();
                    commandeDTO.setId(commande.getId());
                    commandeDTO.setDateHeureDEnvoi(commande.getDateHeureDEnvoi());
                    // You may need to map ligneCommandes as well
                    return commandeDTO;
                })
                .collect(Collectors.toList());
        dto.setCommandes(commandesDTO);

        return dto;
    }


    private List<JardinierDTO> mapToDtoList(List<Jardinier> jardiniers) {
        return jardiniers.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
