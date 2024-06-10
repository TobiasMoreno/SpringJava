package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.match.MatchDto;
import ar.edu.utn.frc.tup.lciii.dtos.play.PlayRequest;
import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.models.*;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchEntityFactory;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    private static final Long APP_PLAYER_ID = 10000L;

    @Autowired
    MatchJpaRepository matchJpaRepository;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameService gameService;
    @Qualifier("modelMaper")
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PlayStrategyFactory playStrategyFactory;

    @Override
    public List<Match> getMatchesByPlayer(Long playerId) {
        List<Match> matches = new ArrayList<>();
        Optional<List<MatchEntity>> optionalMatchEntities = matchJpaRepository.getAllByPlayer1Id(playerId);
        if (optionalMatchEntities.isPresent()) {
            optionalMatchEntities.get().forEach(
                    //Al tener factoria, el foreach no debe mapear a Match.class xq es abstracta
                    //entonces tengo que ingresar por matchFactory y llegar al tipo de class para map
                    me -> {
                        matches.add(modelMapper.map(me, MatchFactory.getTypeOfMatch(me.getGame().getCode())));
                    }
            );
        }
        return matches;
    }

    @Override
    public Match createMatch(MatchDto matchDto) {
        Player player = playerService.getPlayerByid(matchDto.getPlayerId());
        Game game = gameService.getGame(matchDto.getGameId());
        Match match = MatchFactory.createMatch(player, game);
        MatchEntity matchEntity = matchJpaRepository.save(modelMapper.map(match, MatchEntityFactory.getTypeOfMatch(match)));

        return modelMapper.map(matchEntity, match.getClass());
    }

    @Override
    public Match getMatchById(Long id) {
        MatchEntity matchEntity = (MatchEntity) Hibernate.unproxy(matchJpaRepository.getReferenceById(id));
        if (matchEntity != null) {
            Match match = modelMapper.map(matchEntity, MatchFactory.getTypeOfMatch(matchEntity.getGame().getCode()));
            return match;
        } else {
            throw new EntityNotFoundException();
        }
    }

    //Funciona igual que la transaction en la BD, si no se hace el ciclo completo, se hace rollback
    @Transactional
    @Override
    public Play play(Long matchId, PlayRequest playRequest) {
        Match match = this.getMatchById(matchId);
        if (match == null) {
            throw new EntityNotFoundException();
        }
        if (match.getStatus() != MatchStatus.STARTED) {
            // HttpStatus.CONFLICT --> Esta respuesta es enviada cuando una respuesta entra en conflicto con el estado del servidor
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("The match is %s", match.getStatus()));
        }
        Play play = PlayFactory.getPlayInstance(playRequest, match.getGame().getCode());
        PlayMatch playMatch = playStrategyFactory.getPlayStrategy(match.getGame().getCode());
        return playMatch.play(play, match);
    }
}
