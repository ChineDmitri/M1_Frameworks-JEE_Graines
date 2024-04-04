package fr.graines.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import fr.graines.business.*;
import jakarta.transaction.Transactional;
import org.apache.tomcat.Jar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional(rollbackOn = JardinierRepositoryTest.class)
class JardinierRepositoryTest {

    @Autowired
    JardinierRepository jardinierRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private SachetRepository sachetRepository;
    @Autowired
    private TypeDeGraineRepository typeDeGraineRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    protected Jardinier jardinierRetrievedGlobal = null;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    @Test
    @Rollback
    void testSaveJardinier() {
        Jardinier jardiniere = new Jardinier();
        jardiniere.setDateNaissance(LocalDate.of(2001,01,01));

        this.jardinierRetrievedGlobal = jardinierRepository.save(jardiniere);

        assertNotNull(jardinierRetrievedGlobal);
        assertEquals(jardinierRetrievedGlobal.getId(), jardiniere.getId());
    }

    @Test
    @Rollback
    void testFindAllOrderByCommandesDesc() {
        Jardinier jardinierLast   = new Jardinier();
        Jardinier jardinierFirst  = new Jardinier();
        Jardinier jardinierMiddle = new Jardinier();

        jardinierLast.setDateNaissance(LocalDate.of(2001,01,01));
        jardinierLast.setCommandes(new ArrayList<>());
        jardinierRepository.save(jardinierLast);

        jardinierFirst.setDateNaissance(LocalDate.of(2001,01,01));
        jardinierFirst.setCommandes(new ArrayList<>());
        jardinierFirst.getCommandes().add(new Commande());
        jardinierFirst.getCommandes().add(new Commande());
        jardinierRepository.save(jardinierFirst);

        jardinierMiddle.setDateNaissance(LocalDate.of(2001,01,01));
        jardinierMiddle.setCommandes(new ArrayList<>());
        jardinierMiddle.getCommandes().add(new Commande());
        jardinierRepository.save(jardinierMiddle);

        List<Jardinier> jardiniersByQuantityCommandes = jardinierRepository.findAllOrderByCountCommandesDesc();
        List<Jardinier> allJardiniers                 = jardinierRepository.findAll();

        System.err.println(jardiniersByQuantityCommandes.size());
        System.err.println(jardiniersByQuantityCommandes.get(0));
        System.err.println(jardiniersByQuantityCommandes.get(1));
        System.err.println(jardiniersByQuantityCommandes.get(2));

        assertTrue(jardiniersByQuantityCommandes.size() >= 3);
        assertEquals(3, jardiniersByQuantityCommandes.size());
    }

    @Test
    @Rollback
    void testFindJardiniersAvecBasilic() {
        // Création de la graine de basilic
        final TypeDeGraine basilic = new TypeDeGraine();
        basilic.setNom("Basilic");
        basilic.setSemaineDePlantationMin(1);
        basilic.setSemaineDePlantationMax(12);

        // Création du sachet
        final Sachet sachet = new Sachet();
        sachet.setTypeDeGraine(basilic);
        sachet.setPrixEnEuros(15.0f);
        sachet.setTypeDeGraine(basilic);
        // Ajout dans sachet au type de graine
        basilic.getSachets().add(sachet);

        // Création de la ligne de commande
        final LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setSachet(sachet);
        ligneCommande.setQuantite(2);
        sachet.getLigneCommandes().add(ligneCommande);

        // Création de la commande
        final Commande commande = new Commande();
        commande.getLigneCommandes().add(ligneCommande);
        commande.setDateHeureDEnvoi(LocalDateTime.now());
        ligneCommande.setCommande(commande);

        // Création du jardinier
        final Jardinier jardinier = new Jardinier();
        jardinier.setNom("Ceren");
        jardinier.setPrenom("Carlos");
        jardinier.setDateNaissance(LocalDate.of(2001,07,28));
        jardinier.getCommandes().add(commande);
        commande.setJardinier(jardinier);

        final Jardinier jardinier1 = new Jardinier();
        jardinier1.setPrenom("jardinier");
        jardinier1.setNom("jardinier");
        jardinier.setDateNaissance(LocalDate.of(2002,07,28));

        sachetRepository.save(sachet);
        typeDeGraineRepository.save(basilic);
        ligneCommandeRepository.save(ligneCommande);
        commandeRepository.save(commande);
        jardinierRepository.save(jardinier);
        jardinierRepository.save(jardinier1);

        final List<Jardinier> jardiniers = jardinierRepository.findByTypeDeGraineNom("Basilic");

        assertFalse(jardiniers.isEmpty());
        assertEquals(1, jardiniers.size());
        assertEquals("Carlos", jardiniers.get(0).getPrenom());
    }

    @Test
    @Rollback
    void findJardiniersDePlusDe60AnsTest(){
        final Jardinier j1 = new Jardinier();
        j1.setNom("J1");
        j1.setDateNaissance(LocalDate.of(1964,02,10));
        jardinierRepository.save(j1);

        final Jardinier j2 = new Jardinier();
        j2.setNom("J2");
        j2.setDateNaissance(LocalDate.of(1960,01,01));
        jardinierRepository.save(j2);

        final Jardinier j3 = new Jardinier();
        j3.setNom("J3");
        j3.setDateNaissance(LocalDate.of(2001,01,01));
        jardinierRepository.save(j3);

        final LocalDate dateLimite = LocalDate.now().minusYears(60);

        final List<Jardinier> jardiniers = jardinierRepository.findJardiniersPlusDe60Ans(dateLimite);

        assertFalse(jardiniers.isEmpty());
        assertEquals(2, jardiniers.size());
    }
}
