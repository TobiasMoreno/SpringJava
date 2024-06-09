package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lciii.dtos.match.MatchDto;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.services.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/matches")
public class MatchController {

	@Autowired
	private MatchService matchService;

	@Operation(summary = "Create a new Match",
			description = "Return the match created.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation",
					content = @Content(schema = @Schema(implementation = String.class))),
			@ApiResponse(responseCode = "500", description = "Internet Server Error",
					content = @Content(schema = @Schema(implementation = ErrorApi.class)))
	})
	@PostMapping()
	public ResponseEntity<Match> saveMatch(@RequestBody @Valid MatchDto matchDto) {
		Match matchSaved = matchService.createMatch(matchDto);
		if(Objects.isNull(matchSaved)){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid match");
		} else{
			return ResponseEntity.ok(matchSaved);
		}
	}
}
