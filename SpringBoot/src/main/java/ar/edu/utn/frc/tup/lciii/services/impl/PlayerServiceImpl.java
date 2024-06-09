package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.PlayerJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerServiceImpl  implements PlayerService {

    @Autowired
    private PlayerJpaRepository playerRepository;


    @Qualifier("modelMaper")
    @Autowired
    private ModelMapper mapper;
    @Override
    public Player getPlayerByid(Long id) {
        PlayerEntity playerEntity = playerRepository.getReferenceById(id);

        if(Objects.isNull(playerEntity.getUserName())){

            throw new EntityNotFoundException();
        }
        Player player = mapper.map(playerEntity, Player.class);

        return player;
    }

    @Override
    public Player savePlayer(Player player) {
        Optional<PlayerEntity>playerEntityOptional =  playerRepository.findByUserNameOrEmail(
                player.getUserName(), player.getEmail());

        if(playerEntityOptional.isEmpty()){

            PlayerEntity playerEntity = mapper.map(player , PlayerEntity.class); //convertimos el obj por parametro , en tipo entity
            PlayerEntity playerEntitySaved = playerRepository.save(playerEntity); // guaradamos el player en la bd

            return mapper.map(playerEntitySaved, Player.class); // convierto el playerEntity en player.
        }
        return null;
    }
}
