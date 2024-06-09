package ar.edu.utn.frc.tup.lciii;
import static org.assertj.core.api.Assertions.assertThat;
import ar.edu.utn.frc.tup.lciii.controllers.PlayerController;
import ar.edu.utn.frc.tup.lciii.controllers.PingController;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * verifica que nuestro contexto levanta todos los beans que necesitamos
 */
@SpringBootTest
public class SmokeTest {
    @Autowired
    private PlayerController playerController;
    @Autowired
    private PingController pingController;
    @Autowired
    private PlayerService playerService;

    @Test
    void contextLoads() throws Exception {
        assertThat(playerController).isNotNull();
        assertThat(pingController).isNotNull();
        assertThat(playerService).isNotNull();
    }
}
