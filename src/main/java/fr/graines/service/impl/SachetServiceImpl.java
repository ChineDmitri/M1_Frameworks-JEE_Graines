package fr.graines.service.impl;

import fr.graines.business.Fournisseur;
import fr.graines.business.Jardinier;
import fr.graines.business.Sachet;
import fr.graines.business.TypeDeGraine;
import fr.graines.dto.*;
import fr.graines.repository.SachetRepository;
import fr.graines.service.SachetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SachetServiceImpl implements SachetService {

    private final SachetRepository sachetRepository;

    @Autowired
    public SachetServiceImpl(SachetRepository sachetRepository) {
        this.sachetRepository = sachetRepository;
    }

    @Override
    public List<SachetDTO> getAllSachets() {
        return mapToDtoList(sachetRepository.findAll());
    }

    @Override
    public List<SachetDTO> getSachetsNonCommandes() {
        return mapToDtoList(sachetRepository.findSachetsNonCommandes());
    }

    @Override
    public List<SachetDTO> getSachetsOrderByQuantiteCommandeeDesc() {
        return mapToDtoList(sachetRepository.findSachetsOrderByQuantiteCommandeeDesc());
    }

    private SachetDTO mapToDto(Sachet sachet) {
        SachetDTO dto = new SachetDTO();
        dto.setId(sachet.getId());
        dto.setPoidsEnGrammes(sachet.getPoidsEnGrammes());
        dto.setPrixEnEuros(sachet.getPrixEnEuros());
        dto.setTypeDeGraine(mapTypeDeGraineDTO(sachet.getTypeDeGraine()));
        dto.setFournisseur(mapFournisseurDTO(sachet.getFournisseur()));
        return dto;
    }

    private TypeDeGraineDTO mapTypeDeGraineDTO(TypeDeGraine typeDeGraine) {
        TypeDeGraineDTO dto = new TypeDeGraineDTO();
        dto.setId(typeDeGraine.getId());
        dto.setNom(typeDeGraine.getNom());
        dto.setDescription(typeDeGraine.getDescription());
        dto.setSemaineDePlantationMin(typeDeGraine.getSemaineDePlantationMin());
        dto.setSemaineDePlantationMax(typeDeGraine.getSemaineDePlantationMax());
        dto.setEspacementEntrePiedsEnCentimetres(typeDeGraine.getEspacementEntrePiedsEnCentimetres());
        dto.setEspacementEntreLignesEnCentimetres(typeDeGraine.getEspacementEntreLignesEnCentimetres());
        dto.setConseils(typeDeGraine.getConseils());
        if (typeDeGraine.getFamille() != null) {
            dto.setFamille(typeDeGraine.getFamille());
        }
        return dto;
    }

    private FournisseurDTO mapFournisseurDTO(final Fournisseur fournisseur) {
        FournisseurDTO dto = new FournisseurDTO();
        dto.setId(fournisseur.getId());
        dto.setMotDePasse(fournisseur.getMotDePasse());
        dto.setAdresseEmail(fournisseur.getAdresseEmail());
        dto.setNom(fournisseur.getNom());
        dto.setPrenom(fournisseur.getPrenom());
        dto.setNumeroDePortable(fournisseur.getNumeroDePortable());

        return dto;
    }

    private List<SachetDTO> mapToDtoList(List<Sachet> sachets) {
        return sachets.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
