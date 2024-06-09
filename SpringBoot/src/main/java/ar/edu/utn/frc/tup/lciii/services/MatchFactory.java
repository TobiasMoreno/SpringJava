package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.rps.MatchRps;

public class MatchFactory {
	public static final Match createMatch(String gameCode) {
		switch (gameCode) {
			case "RPS":
				return new MatchRps();
			default:
				return new MatchRps();
		}
	}
}
