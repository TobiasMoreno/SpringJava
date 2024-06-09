package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Operation(summary = "Description of the method. Get Player by Id",
            description = "Description of the method. Return a player by th id. If the player doesn´t exist return 404")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internet Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable long id) {

        return ResponseEntity.ok(playerService.getPlayerByid(id));
    }

    @Operation(summary = "Create a new Player",
            description = "Return the player created with te id. If a player exist with the username or email, return 404." +
                    "Additionally, the email must be valid and the pass must have at least 8 characters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Username or Email already exists",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class))),
            @ApiResponse(responseCode = "500", description = "Internet Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    @PostMapping()
    public ResponseEntity<Player> savePlayer(@RequestBody @Valid Player player) {

        Player newPlayer = playerService.savePlayer(player);

        if (newPlayer == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario o el email , ya existen");
        }

        return ResponseEntity.ok(newPlayer);
    }
}
