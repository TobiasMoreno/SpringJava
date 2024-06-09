package ar.edu.utn.frc.tup.lciii.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayerJpaRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlayerJpaRepository playerJpaRepository;
    @Test
    void findByUserNameOrEmail() {
        PlayerEntity player = new PlayerEntity();
        player.setEmail("tobiasmoreno.tm.21@gmail.com");
        player.setUserName("Tobias");
        player.setPassword("Contraseña");

        entityManager.persist(player);
        entityManager.flush();

       Optional<PlayerEntity> result = playerJpaRepository.findByUserNameOrEmail(player.getUserName() , player.getEmail());

       assertEquals("Tobias" , result.get().getUserName());
       assertEquals("tobiasmoreno.tm.21@gmail.com" , result.get().getEmail());
    }
}