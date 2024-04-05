package fr.graines.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import fr.graines.dto.TypeDeGraineDTO;
import fr.graines.service.TypeDeGraineService;

@Controller
public class TypeDeGraineController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TypeDeGraineController.class);

    @Autowired
    private TypeDeGraineService typeDeGraineService;

    @GetMapping("/types-de-graines")
    public String showTypesDeGraines(Model model) {
        LOGGER.info("Listage de grainnes");
        List<TypeDeGraineDTO> typesDeGraines = typeDeGraineService.getAllTypeDeGraines();
        model.addAttribute("typesDeGraines", typesDeGraines);
        return "liste-types-de-graines-page";
    }
}
