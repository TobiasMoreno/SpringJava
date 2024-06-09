package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Player;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {

    Player getPlayerByid(Long id);

    Player savePlayer(Player player);
}