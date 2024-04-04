package fr.graines.service.impl;

import fr.graines.business.TypeDeGraine;
import fr.graines.dto.TypeDeGraineDTO;
import fr.graines.repository.TypeDeGraineRepository;
import fr.graines.service.TypeDeGraineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeDeGraineServiceImpl implements TypeDeGraineService {

    private final TypeDeGraineRepository typeDeGraineRepository;

    @Autowired
    public TypeDeGraineServiceImpl(TypeDeGraineRepository typeDeGraineRepository) {
        this.typeDeGraineRepository = typeDeGraineRepository;
    }

    @Override
    public List<TypeDeGraineDTO> getAllTypeDeGraines() {
        return mapToDtoList(typeDeGraineRepository.findAll());
    }

    @Override
    public List<TypeDeGraineDTO> getTypesDeGrainesPourPlantation(int currentWeek) {
        return mapToDtoList(typeDeGraineRepository.findTypesDeGrainePourPlantation(currentWeek));
    }

    @Override
    public List<TypeDeGraineDTO> getTypesDeGraineTrieSurNombreDeRecettes() {
        return mapToDtoList(typeDeGraineRepository.findTypesDeGraineTrieSurNombreDeRecettes());
    }

    private TypeDeGraineDTO mapToDto(TypeDeGraine typeDeGraine) {
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

    private List<TypeDeGraineDTO> mapToDtoList(List<TypeDeGraine> typeDeGraines) {
        return typeDeGraines.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
