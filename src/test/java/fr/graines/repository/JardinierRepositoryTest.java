package fr.graines.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import fr.graines.business.Commande;
import fr.graines.business.Jardinier;
import fr.graines.business.Utilisateur;

class JardinierRepositoryTest extends
                              UtilisateurRepositoryTest {

    @Autowired
    JardinierRepository           jardinierRepositoryUnderTest;
    @Autowired
    private UtilisateurRepository utilisateurRepositoryUnderTest;

    protected List<Utilisateur> usersRetrieved           = null;
    protected Jardinier         jardinierRetrievedGlobal = null;

    @BeforeEach
    void setUp() throws Exception {
        this.testSaveUtilisateur();
        usersRetrieved = utilisateurRepositoryUnderTest.findAll();
    }

    @AfterEach
    void tearDown() throws Exception {}

    @Test
    void testSaveJardinier() {
        Jardinier jardiniere = new Jardinier();
        jardiniere.setDateDeNaissance(LocalDateTime.now());

        this.jardinierRetrievedGlobal = jardinierRepositoryUnderTest.save(jardiniere);

        assertNotNull(jardinierRetrievedGlobal);
        assertEquals(jardinierRetrievedGlobal.getId(), jardiniere.getId());
    }

    // @Test
    // void addJardiniereToUser() {
    // Jardinier jardiniere = new Jardinier();
    // jardiniere.setDateDeNaissance(LocalDateTime.now());
    // /* Jardinier jardiniereRetrievedInTest = */jardinierRepositoryUnderTest.save(jardiniere);
    //
    // Utilisateur userUnderTest = usersRetrieved.get(0);
    // userUnderTest.setJardinier(jardiniere);
    //
    // utilisateurRepositoryUnderTest.save(userUnderTest);
    // Optional<Utilisateur> userRetrieved =
    // utilisateurRepositoryUnderTest.findById(userUnderTest.getId());
    //
    // jardiniere.setUtilisateur(userUnderTest);
    // Jardinier jardiniereRetrievedInTest = jardinierRepositoryUnderTest.save(jardiniere);
    //
    // System.err.println("User avant sauvgarde " + userUnderTest);
    // System.err.println("User depuis bdd sauvgarde " + userRetrieved.get());
    // System.err.println("User from jardiniere " + jardiniereRetrievedInTest);
    //
    // assertNotNull(userRetrieved.get().getJardinier());
    // assertNotNull(jardiniereRetrievedInTest.getUtilisateur());
    // assertEquals(jardiniereRetrievedInTest.getId(), userRetrieved.get()
    // .getJardinier()
    // .getId());
    // }
    @Test
    void addJardiniereToUser() {
        Jardinier jardiniere = new Jardinier();
        jardiniere.setDateDeNaissance(LocalDateTime.now());
        jardinierRepositoryUnderTest.save(jardiniere);

        Utilisateur userUnderTest = usersRetrieved.get(0);
        userUnderTest.setJardinier(jardiniere);
        utilisateurRepositoryUnderTest.save(userUnderTest);

        Optional<Utilisateur> userRetrieved = utilisateurRepositoryUnderTest.findById(userUnderTest.getId());

        System.err.println("User avant sauvgarde " + userUnderTest);
        System.err.println("User depuis bdd sauvgarde " + userRetrieved.get());

        assertNotNull(userRetrieved.get().getJardinier());
        assertEquals(jardiniere.getId(), userRetrieved.get().getJardinier().getId());
    }

    @Test
    void testFindAllOrderByCommandesDesc() {
        // Создаем несколько садоводов с разным количеством команд
        Jardinier jardinierLast   = new Jardinier();
        Jardinier jardinierFirst  = new Jardinier();
        Jardinier jardinierMiddle = new Jardinier();

        jardinierLast.setDateDeNaissance(LocalDateTime.now());
        jardinierLast.setCommandes(new ArrayList<>());
        jardinierRepositoryUnderTest.save(jardinierLast);

        jardinierFirst.setDateDeNaissance(LocalDateTime.now());
        jardinierFirst.setCommandes(new ArrayList<>());
        jardinierFirst.getCommandes().add(new Commande());
        jardinierFirst.getCommandes().add(new Commande());
        jardinierRepositoryUnderTest.save(jardinierFirst);

        jardinierMiddle.setDateDeNaissance(LocalDateTime.now());
        jardinierMiddle.setCommandes(new ArrayList<>());
        jardinierMiddle.getCommandes().add(new Commande());
        jardinierRepositoryUnderTest.save(jardinierMiddle);

        List<Jardinier> jardiniersByQuantityCommandes = jardinierRepositoryUnderTest.findAllOrderByCountCommandesDesc();
        List<Jardinier> allJardiniers                 = jardinierRepositoryUnderTest.findAll();

        System.err.println(jardiniersByQuantityCommandes.size());
        System.err.println(jardiniersByQuantityCommandes.get(0));
        System.err.println(jardiniersByQuantityCommandes.get(1));
        System.err.println(jardiniersByQuantityCommandes.get(2));

        assertTrue(jardiniersByQuantityCommandes.size() >= 3);
        assertEquals(jardiniersByQuantityCommandes.size(), 3);
    }

}
