package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Game;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.models.rps.MatchRps;
import ar.edu.utn.frc.tup.lciii.models.rps.PlayRps;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MatchFactory {

	public static Match createMatch(Player player, Game game) {
		switch (game.getCode()) {
			case "RPS":
				return CreateMatchRps(player, game);
			default:
				return CreateMatchRps(player, game);
		}
	}

	public static Class<? extends Match> getTypeOfMatch(String gameCode){
		switch (gameCode){
			case "RPS":
				return MatchRps.class;
			default:
				return MatchRps.class;
		}
	}

	private static Match CreateMatchRps(Player player, Game game) {
		MatchRps matchRps = (MatchRps) getBasicMatch(player, game);
		matchRps.setNumberOfPlays(10);
		matchRps.setRemainderPlays(10);
		matchRps.setPlayer1Score(0);
		matchRps.setPlayer2Score(0);
		matchRps.setPlayRps(new ArrayList<PlayRps>());
		return matchRps;
	}

	private static Match getBasicMatch(Player player, Game game) {
		Match match = getMatchInstance(game.getCode());
		match.setPlayer(player);
		match.setGame(game);
		match.setCreatedDate(LocalDateTime.now());
		match.setStatus(MatchStatus.STARTED);
		return match;
	}

	private static Match getMatchInstance(String code) {
		switch (code) {
			case "RPS":
				return new MatchRps();
			default:
				return new MatchRps();
		}
	}


}
