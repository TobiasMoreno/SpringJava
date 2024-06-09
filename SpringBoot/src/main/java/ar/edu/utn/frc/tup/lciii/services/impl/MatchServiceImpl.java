package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.match.MatchDto;
import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.models.Game;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.models.rps.MatchRps;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import ar.edu.utn.frc.tup.lciii.services.MatchFactory;
import ar.edu.utn.frc.tup.lciii.services.MatchService;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	MatchJpaRepository matchJpaRepository;
	@Autowired
	private PlayerService playerService;
	@Autowired
	private GameService gameService;
	@Qualifier("modelMaper")
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Match> getMatchesByPlayer(Long playerId) {
		List<Match> matches = new ArrayList<>();
		Optional<List<MatchEntity>> optionalMatchEntities = matchJpaRepository.getAllByPlayerId(playerId);
		if (optionalMatchEntities.isPresent()) {
			optionalMatchEntities.get().forEach(
					//Al tener factoria, el foreach no debe mapear a Match.class xq es abstracta
					//entonces tengo que ingresar por matchFactory y llegar al tipo de class para map
					me -> {
						matches.add(modelMapper.map(me, MatchFactory.createMatch(me.getGame().getCode()).getClass()));
					}
			);

//			for (MatchEntity matchEntity : optionalMatchEntities.get()) {
//				matches.add(modelMapper.map(matchEntity, Match.class));
//			}
		}
		return matches;
	}

	@Override
	public Match createMatch(MatchDto matchDto) {
		Player player = playerService.getPlayerByid(matchDto.getPlayerId());
		Game game = gameService.getGame(matchDto.getGameId());
		Match match = MatchFactory.createMatch(game.getCode());
		match.setPlayer(player);
		match.setGame(game);
		match.setCreatedDate(LocalDateTime.now());
		match.setStatus(MatchStatus.STARTED);
		MatchEntity matchEntity = matchJpaRepository.save(modelMapper.map(match, MatchEntity.class));

		return modelMapper.map(matchEntity, Match.class);
	}
}
