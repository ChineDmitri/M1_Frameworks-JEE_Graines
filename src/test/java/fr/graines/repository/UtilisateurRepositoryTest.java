package fr.graines.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import fr.graines.business.Utilisateur;

@TestInstance(Lifecycle.PER_CLASS)
/* @ExtendWith(MockitoExtension.class) */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepositoryUnderTest;

    @BeforeAll
    void setUpBeforeClass() throws Exception {}

    @AfterAll
    void tearDownAfterClass() throws Exception {}

    @BeforeEach
    void setUp() throws Exception {}

    @AfterEach
    void tearDown() throws Exception {}

    @Test
    public void testSaveUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("John");
        utilisateur.setPrenom("Doe");
        utilisateur.setAdresseEmail("john.doe@example.com");
        utilisateur.setMotDePasse("password");

        Utilisateur savedUtilisateur = utilisateurRepositoryUnderTest.save(utilisateur);

        assertEquals(savedUtilisateur.getId(), utilisateur.getId());
        assertThat(savedUtilisateur).isNotNull();
        assertNotNull(savedUtilisateur.getId());
        assertThat(savedUtilisateur.getNom()).isEqualTo(utilisateur.getNom());
        assertThat(savedUtilisateur.getPrenom()).isEqualTo(utilisateur.getPrenom());
        assertThat(savedUtilisateur.getAdresseEmail()).isEqualTo(utilisateur.getAdresseEmail());
        assertThat(savedUtilisateur.getMotDePasse()).isEqualTo(utilisateur.getMotDePasse());
    }

}
