package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    Player getPlayerByid(Long id);
    Player savePlayer(Player player);
    Player getPlayerByUserNameAndPassword(String userName, String password);
    Player getPlayerByEmailAndPassword(String email, String password);
    Player getPlayerByUserNameOrEmailAndPassword(String identity, String password);

}
