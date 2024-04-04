package fr.graines.service;

import fr.graines.dto.TypeDeGraineDTO;

import java.util.List;

public interface TypeDeGraineService {

    List<TypeDeGraineDTO> getAllTypeDeGraines();

    List<TypeDeGraineDTO> getTypesDeGrainesPourPlantation(final int currentWeek);

    List<TypeDeGraineDTO> getTypesDeGraineTrieSurNombreDeRecettes();
}
