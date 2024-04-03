package fr.graines.repository;

import fr.graines.business.Sachet;
import fr.graines.business.TypeDeGraine;
import fr.graines.business.Famille;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SachetRepositoryTest {

    @Autowired
    private SachetRepository sachetRepository;

    @Test
    void testFindSachetsNonCommandes() {
        // Création de données de test : un sachet non commandé
        Sachet sachetNonCommande = Sachet.builder()
                .poidsEnGrammes(100)
                .prixEnEuros(5.0f)
                .typeDeGraine(new TypeDeGraine())
                .famille(new Famille())
                .build();
        sachetRepository.save(sachetNonCommande);

        List<Sachet> sachetsNonCommandes = sachetRepository.findSachetsNonCommandes();

        assertEquals(1, sachetsNonCommandes.size());
        assertEquals(sachetNonCommande, sachetsNonCommandes.get(0));
    }

    @Test
    void testFindAllOrderByQuantiteCommandeeDesc() {
        // Création de données de test : deux sachets avec différentes quantités commandées
        Sachet sachet1 = Sachet.builder()
                .poidsEnGrammes(100)
                .prixEnEuros(5.0f)
                .typeDeGraine(new TypeDeGraine())
                .famille(new Famille())
                .build();
        sachetRepository.save(sachet1);

        Sachet sachet2 = Sachet.builder()
                .poidsEnGrammes(200)
                .prixEnEuros(10.0f)
                .typeDeGraine(new TypeDeGraine())
                .famille(new Famille())
                .build();
        sachetRepository.save(sachet2);

        List<Sachet> sachetsTries = sachetRepository.findAllOrderByQuantiteCommandeeDesc();

        assertEquals(2, sachetsTries.size());
        assertEquals(sachet2, sachetsTries.get(0)); // sachet2 doit être en premier car il a une quantité commandée supérieure
        assertEquals(sachet1, sachetsTries.get(1)); // sachet1 doit être en second car il a une quantité commandée inférieure
    }
}
