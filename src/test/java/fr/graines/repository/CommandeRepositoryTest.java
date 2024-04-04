package fr.graines.repository;

import fr.graines.business.Commande;
import fr.graines.business.LigneCommande;
import fr.graines.business.Sachet;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional(rollbackOn = CommandeRepository.class)
class CommandeRepositoryTest {
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private SachetRepository sachetRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    private JardinierRepository jardinierRepository;

    @BeforeEach
    void clearDatabase() {
        commandeRepository.deleteAll();
        ligneCommandeRepository.deleteAll();
        sachetRepository.deleteAll();
        jardinierRepository.deleteAll();
    }

    @Test
    void testFindByDateHeureDEnvoiBetween() {
        // Création de quelques commandes avec des dates d'envoi
        final LocalDateTime dateHeureDEnvoiStart = LocalDateTime.of(2024, 3, 1, 0, 0);
        final LocalDateTime dateHeureDEnvoiEnd = LocalDateTime.of(2024, 3, 31, 23, 59);

        // Création de commandes avec des dates d'envoi et des lignes de commande
        final Commande commande1 = new Commande();
        commande1.setDateHeureDEnvoi(LocalDateTime.of(2024, 2, 15, 10, 0));
        final LigneCommande ligneCommande1 = new LigneCommande();
        ligneCommande1.setCommande(commande1);
        commande1.setLigneCommandes(Collections.singletonList(ligneCommande1));
        commandeRepository.save(commande1);

        final Commande commande2 = new Commande();
        commande2.setDateHeureDEnvoi(LocalDateTime.of(2024, 3, 5, 12, 0));
        final LigneCommande ligneCommande2 = new LigneCommande();
        ligneCommande2.setCommande(commande2);
        commande2.setLigneCommandes(Collections.singletonList(ligneCommande2));
        commandeRepository.save(commande2);

        final Commande commande3 = new Commande();
        commande3.setDateHeureDEnvoi(LocalDateTime.of(2024, 4, 10, 15, 0));
        final LigneCommande ligneCommande3 = new LigneCommande();
        ligneCommande3.setCommande(commande3);
        commande3.setLigneCommandes(Collections.singletonList(ligneCommande3));
        commandeRepository.save(commande3);

        // Appel de la méthode findByDateHeureDEnvoiBetween
        final List<Commande> commandesBetweenDates = commandeRepository.findByDateHeureDEnvoiBetween(dateHeureDEnvoiStart, dateHeureDEnvoiEnd);

        // Vérification
        assertEquals(1, commandesBetweenDates.size());
        assertTrue(commandesBetweenDates.contains(commande2));
    }

    @Test
    void findAllOrderByMontantTotalDescTest(){
        final Commande commande1 = new Commande();
        commande1.setDateHeureDEnvoi(LocalDateTime.of(2024, 2, 15, 10, 0));
        // Création de la ligne de commande
        final LigneCommande ligneCommande1 = new LigneCommande();
        ligneCommande1.setCommande(commande1);
        ligneCommande1.setQuantite(5);
        // Création du sachet
        final Sachet sachet1 = new Sachet();
        sachet1.setPrixEnEuros(20.0f);
        sachet1.getLigneCommandes().add(ligneCommande1);
        ligneCommande1.setSachet(sachet1);
        commande1.setLigneCommandes(Collections.singletonList(ligneCommande1));
        // Sauvegarde des données
        sachetRepository.save(sachet1);
        ligneCommandeRepository.save(ligneCommande1);
        commandeRepository.save(commande1);

        final Commande commande2 = new Commande();
        commande2.setDateHeureDEnvoi(LocalDateTime.of(2024, 2, 15, 10, 0));
        // Création de la ligne de commande
        final LigneCommande ligneCommande2 = new LigneCommande();
        ligneCommande2.setCommande(commande2);
        ligneCommande2.setQuantite(100);
        // Création du sachet
        final Sachet sachet2 = new Sachet();
        sachet2.setPrixEnEuros(10.0f);
        sachet2.getLigneCommandes().add(ligneCommande2);
        ligneCommande2.setSachet(sachet2);
        commande2.setLigneCommandes(Collections.singletonList(ligneCommande2));
        // Sauvegarde des données
        sachetRepository.save(sachet2);
        ligneCommandeRepository.save(ligneCommande2);
        commandeRepository.save(commande2);

        final List<Commande> commandes = commandeRepository.findAllOrderByMontantTotalDesc();

        assertFalse(commandes.isEmpty());
        assertEquals(2, commandes.size());
        assertEquals(5, commandes.get(0).getId());
        assertEquals(4, commandes.get(1).getId());
    }

    @Test
    void countCommandesParMoisTest() {
        // Création de commandes avec différentes dates d'envoi
        final Commande commande1 = new Commande();
        commande1.setDateHeureDEnvoi(LocalDateTime.of(2024, 3, 5, 10, 0));
        final LigneCommande l1 = new LigneCommande();
        l1.setCommande(commande1);
        commande1.getLigneCommandes().add(l1);
        ligneCommandeRepository.save(l1);
        commandeRepository.save(commande1);

        final Commande commande2 = new Commande();
        commande2.setDateHeureDEnvoi(LocalDateTime.of(2024, 3, 15, 10, 0));
        final LigneCommande l2 = new LigneCommande();
        l2.setCommande(commande2);
        commande2.getLigneCommandes().add(l2);
        ligneCommandeRepository.save(l2);
        commandeRepository.save(commande2);

        final Commande commande3 = new Commande();
        commande3.setDateHeureDEnvoi(LocalDateTime.of(2024, 4, 10, 10, 0));
        final LigneCommande l3 = new LigneCommande();
        l3.setCommande(commande3);
        commande3.getLigneCommandes().add(l3);
        ligneCommandeRepository.save(l3);
        commandeRepository.save(commande3);

        // Appel de la méthode countCommandesParMois
        final List<Map<Month, Integer>> result = commandeRepository.countCommandesParMois();

        // Vérification
        assertEquals(2, result.size()); // On s'attend à avoir deux mois différents
        assertEquals(3, result.get(0).get("mois"));

    }
}
