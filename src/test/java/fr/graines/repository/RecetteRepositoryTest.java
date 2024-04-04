package fr.graines.repository;

import fr.graines.business.Recette;
import fr.graines.business.TypeDeGraine;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Transactional(rollbackOn = RecetteRepositoryTest.class)
class RecetteRepositoryTest {

    @Autowired
    private RecetteRepository recetteRepository;

    @Autowired
    private TypeDeGraineRepository typeDeGraineRepository;

    @Test
    void testFindRecettesTrieSurNombreDeTypesDeGraine() {
        // Créer quelques types de graines
        TypeDeGraine type1 = new TypeDeGraine();
        type1.setNom("Type1");
        type1.setSemaineDePlantationMin(1);
        type1.setSemaineDePlantationMax(52);

        TypeDeGraine type2 = new TypeDeGraine();
        type2.setNom("Type2");
        type2.setSemaineDePlantationMin(1);
        type2.setSemaineDePlantationMax(52);


        // Créer quelques recettes avec différents types de graines associés
        Recette recette1 = new Recette();
        recette1.setIntitule("Recette1");
        recette1.getTypeDeGraines().add(type1);
        type1.getRecettes().add(recette1);
        type2.getRecettes().add(recette1);

        Recette recette2 = new Recette();
        recette2.setIntitule("Recette2");
        recette2.getTypeDeGraines().add(type1);
        type1.getRecettes().add(recette2);
        type2.getRecettes().add(recette2);

        Recette recette3 = new Recette();
        recette3.setIntitule("Recette3");
        recette3.getTypeDeGraines().add(type2);
        recette3.getTypeDeGraines().add(type1);
        type2.getRecettes().add(recette3);


        recetteRepository.save(recette2);
        recetteRepository.save(recette1);
        recetteRepository.save(recette3);
        typeDeGraineRepository.save(type1);
        typeDeGraineRepository.save(type2);

        // Appeler la méthode findRecettesTrieSurNombreDeTypesDeGraine
        List<Recette> recettes = recetteRepository.findRecettesTrieSurNombreDeTypesDeGraine();

        // Vérifier que les recettes sont retournées dans l'ordre décroissant du nombre de types de graines associés
        assertEquals(3, recettes.size());
        assertEquals("Recette3", recettes.get(0).getIntitule());
        assertEquals("Recette2", recettes.get(1).getIntitule());
        assertEquals("Recette1", recettes.get(2).getIntitule());
    }


}
