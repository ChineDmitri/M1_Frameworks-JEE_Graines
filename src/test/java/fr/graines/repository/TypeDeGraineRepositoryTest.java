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
@Transactional(rollbackOn = TypeDeGraineRepositoryTest.class)
class TypeDeGraineRepositoryTest {

    @Autowired
    private TypeDeGraineRepository typeDeGraineRepository;

    @Autowired
    private RecetteRepository recetteRepository;

    @Test
    void testFindTypesDeGrainePourPlantation() {
        // Créer quelques types de graines avec des semaines de plantation valides
        TypeDeGraine type1 = new TypeDeGraine();
        type1.setNom("Type1");
        type1.setSemaineDePlantationMin(1);
        type1.setSemaineDePlantationMax(20);
        typeDeGraineRepository.save(type1);

        TypeDeGraine type2 = new TypeDeGraine();
        type2.setNom("Type2");
        type2.setSemaineDePlantationMin(15);
        type2.setSemaineDePlantationMax(30);
        typeDeGraineRepository.save(type2);

        // Appeler la méthode findTypesDeGrainePourPlantation pour une semaine donnée
        int currentWeek = 10; // Vous pouvez définir une semaine actuelle selon vos besoins
        List<TypeDeGraine> typesPourPlantation = typeDeGraineRepository.findTypesDeGrainePourPlantation(currentWeek);

        // Vérifier que les types de graines retournés correspondent aux semaines de plantation actuelles
        assertEquals(1, typesPourPlantation.size());
        assertEquals("Type1", typesPourPlantation.get(0).getNom());
    }

    @Test
    void testFindTypesDeGraineTrieSurNombreDeRecettes() {
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

        Recette recette2 = new Recette();
        recette2.setIntitule("Recette2");
        recette2.getTypeDeGraines().add(type1);
        type1.getRecettes().add(recette2);

        Recette recette3 = new Recette();
        recette3.setIntitule("Recette3");
        recette3.getTypeDeGraines().add(type2);
        type2.getRecettes().add(recette3);

        recetteRepository.save(recette1);
        recetteRepository.save(recette2);
        recetteRepository.save(recette3);
        typeDeGraineRepository.save(type1);
        typeDeGraineRepository.save(type2);

        // Appeler la méthode findTypesDeGraineTrieSurNombreDeRecettes
        List<TypeDeGraine> typesDeGraine = typeDeGraineRepository.findTypesDeGraineTrieSurNombreDeRecettes();

        // Vérifier que les types de graines sont retournés dans l'ordre décroissant du nombre de recettes associées
        assertEquals(2, typesDeGraine.size());
        assertEquals("Type1", typesDeGraine.get(0).getNom());
        assertEquals("Type2", typesDeGraine.get(1).getNom());
    }
}
