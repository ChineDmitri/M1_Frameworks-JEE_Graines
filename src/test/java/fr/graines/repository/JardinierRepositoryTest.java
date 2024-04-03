package fr.graines.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    void saddJardiniereToUser() {
        Jardinier jardiniere = new Jardinier();
        jardiniere.setDateDeNaissance(LocalDateTime.now());
        Jardinier jardinierRetrievedInTest = jardinierRepositoryUnderTest.save(jardiniere);

        Utilisateur userUnderTest = usersRetrieved.get(0);
        userUnderTest.setJardinier(jardinierRetrievedInTest);

        utilisateurRepositoryUnderTest.save(userUnderTest);
        Optional<Utilisateur> userRetrieved = utilisateurRepositoryUnderTest.findById(userUnderTest.getId());

        System.err.println("User avant sauvgarde " + userUnderTest);
        System.err.println("User depuis bdd sauvgarde " + userRetrieved.get());

        assertNotNull(userRetrieved.get().getJardinier());
        assertEquals(jardinierRetrievedInTest.getId(), userRetrieved.get()
                                                                    .getJardinier()
                                                                    .getId());
    }

}
