package fr.graines.service.impl;

import fr.graines.business.Recette;
import fr.graines.dto.RecetteDTO;
import fr.graines.dto.TypeDeGraineDTO;
import fr.graines.repository.RecetteRepository;
import fr.graines.service.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecetteServiceImpl implements RecetteService {

    private final RecetteRepository recetteRepository;

    @Autowired
    public RecetteServiceImpl(RecetteRepository recetteRepository) {
        this.recetteRepository = recetteRepository;
    }


    @Override
    public List<RecetteDTO> getAllRecettes() {
        return mapToDtoList(recetteRepository.findAll());
    }

    @Override
    public List<RecetteDTO> getRecettesTrieSurNombreDeTypesDeGraine() {
        return mapToDtoList(recetteRepository.findRecettesTrieSurNombreDeTypesDeGraine());
    }

    private RecetteDTO mapToDto(Recette recette) {
        RecetteDTO dto = new RecetteDTO();
        dto.setId(recette.getId());
        dto.setIntitule(recette.getIntitule());
        dto.setContenu(recette.getContenu());

        // Mapping typeDeGraines
        List<TypeDeGraineDTO> typeDeGrainesDTO = recette.getTypeDeGraines().stream()
                .map(typeDeGraine -> {
                    TypeDeGraineDTO typeDeGraineDTO = new TypeDeGraineDTO();
                    typeDeGraineDTO.setId(typeDeGraine.getId());
                    typeDeGraineDTO.setNom(typeDeGraine.getNom());
                    // Map other fields as needed
                    return typeDeGraineDTO;
                })
                .collect(Collectors.toList());
        dto.setTypeDeGraines(typeDeGrainesDTO);

        return dto;
    }

    private List<RecetteDTO> mapToDtoList(List<Recette> recettes) {
        return recettes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

}
