package kz.dilshat.salon.catalog;

import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.containers.PostgreSQLContainer;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import jakarta.persistence.EntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Testcontainers
public class SalonRepositoryTest {

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private EntityManager entityManager;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17");

    @Test
    void contextLoads() {
    }

    @Test
    void createSalon() {
        Salon salon = new Salon("Test Salon","Asia/Almaty");
        Salon savedSalon = salonRepository.save(salon);
        salonRepository.flush();
        entityManager.refresh(savedSalon);
        assertNotNull(savedSalon.getId());

        Optional<Salon> foundSalon =
                salonRepository.findById(savedSalon.getId());
        assertTrue(foundSalon.isPresent());
        Salon found = foundSalon.orElseThrow();
        assertEquals("Test Salon", found.getName());
        assertEquals("Asia/Almaty", found.getTimeZone());
        assertNotNull(found.getCreatedAt());
    }

}
