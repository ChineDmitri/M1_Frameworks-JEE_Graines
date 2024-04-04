package fr.graines.repository;

import fr.graines.business.LigneCommande;
import fr.graines.business.Sachet;
import fr.graines.business.TypeDeGraine;
import fr.graines.business.Famille;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Transactional(rollbackOn = SachetRepositoryTest.class)
class SachetRepositoryTest {

    @Autowired
    private SachetRepository sachetRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    private TypeDeGraineRepository typeDeGraineRepository;

    @Test
    void testFindSachetsOrderByQuantiteCommandeeDesc() {
        // Créer des types de graine
        TypeDeGraine typeDeGraine1 = new TypeDeGraine();
        typeDeGraine1.setNom("TypeDeGraine1");
        typeDeGraine1.setSemaineDePlantationMin(1);
        typeDeGraine1.setSemaineDePlantationMax(52);
        typeDeGraineRepository.save(typeDeGraine1);

        TypeDeGraine typeDeGraine2 = new TypeDeGraine();
        typeDeGraine2.setNom("TypeDeGraine2");
        typeDeGraine2.setSemaineDePlantationMin(1);
        typeDeGraine2.setSemaineDePlantationMax(52);
        typeDeGraineRepository.save(typeDeGraine2);

        // Créer des sachets avec des types de graine associés
        Sachet sachet1 = new Sachet();
        sachet1.setTypeDeGraine(typeDeGraine1);
        sachetRepository.save(sachet1);

        // Créer des lignes de commande associées à chaque sachet
        LigneCommande ligneCommande1 = new LigneCommande();
        ligneCommande1.setSachet(sachet1);
        ligneCommande1.setQuantite(10);
        ligneCommandeRepository.save(ligneCommande1);

        Sachet sachet2 = new Sachet();
        sachet2.setTypeDeGraine(typeDeGraine2);
        sachetRepository.save(sachet2);

        LigneCommande ligneCommande2 = new LigneCommande();

        ligneCommande2.setSachet(sachet2);
        ligneCommande2.setQuantite(20);
        ligneCommandeRepository.save(ligneCommande2);

        // Appeler la méthode findSachetsOrderByQuantiteCommandeeDesc
        List<Sachet> sachets = sachetRepository.findSachetsOrderByQuantiteCommandeeDesc();

        // Vérifier que les sachets sont retournés dans l'ordre décroissant de la quantité commandée
        assertEquals(2, sachets.size());
        assertEquals(sachet2.getId(), sachets.get(0).getId());
        assertEquals(sachet1.getId(), sachets.get(1).getId());
    }

    @Test
    void testFindSachetsNonCommandes() {
        // Créer des types de graine
        TypeDeGraine typeDeGraine1 = new TypeDeGraine();
        typeDeGraine1.setNom("TypeDeGraine1");
        typeDeGraine1.setSemaineDePlantationMin(1);
        typeDeGraine1.setSemaineDePlantationMax(52);
        typeDeGraineRepository.save(typeDeGraine1);

        TypeDeGraine typeDeGraine2 = new TypeDeGraine();
        typeDeGraine2.setNom("TypeDeGraine2");
        typeDeGraine2.setSemaineDePlantationMin(1);
        typeDeGraine2.setSemaineDePlantationMax(52);
        typeDeGraineRepository.save(typeDeGraine2);

        // Créer des sachets avec des types de graine associés
        Sachet sachet1 = new Sachet();
        sachet1.setTypeDeGraine(typeDeGraine1);
        sachetRepository.save(sachet1);

        Sachet sachet2 = new Sachet();
        sachet2.setTypeDeGraine(typeDeGraine2);
        sachetRepository.save(sachet2);

        // Appeler la méthode findSachetsNonCommandes
        List<Sachet> sachetsNonCommandes = sachetRepository.findSachetsNonCommandes();

        // Vérifier que les sachets retournés n'ont jamais été commandés
        assertEquals(2, sachetsNonCommandes.size());
        assertTrue(sachetsNonCommandes.contains(sachet1));
        assertTrue(sachetsNonCommandes.contains(sachet2));
    }

}
