package fr.graines.service;

import fr.graines.dto.RecetteDTO;

import java.util.List;

public interface RecetteService {

    List<RecetteDTO> getAllRecettes();

    List<RecetteDTO> getRecettesTrieSurNombreDeTypesDeGraine();
}
