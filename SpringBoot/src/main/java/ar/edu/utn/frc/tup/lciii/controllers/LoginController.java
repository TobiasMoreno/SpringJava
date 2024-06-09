package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lciii.dtos.login.Credential;
import ar.edu.utn.frc.tup.lciii.dtos.login.CredentialV2;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(summary = "Login a player in the plataform",
            description = "Return the player logged in if the credentials are correct.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))
            ),
            @ApiResponse(responseCode = "404", description = "Credentials Invalid", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(responseCode = "500", description = "Internet Server Error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @PostMapping("")
    public ResponseEntity<Player> loginPlayer(@RequestBody @Valid Credential credential) {
        return ResponseEntity.ok(loginService.login(credential));
    }


    //Login con CredentialV2, otra manera de loguearse
    @Operation(summary = "Login a player in the plataform",
            description = "Return the player logged in if the credentials are correct.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content =
            @Content(schema = @Schema(implementation = Player.class))
            ),
            @ApiResponse(responseCode = "404", description = "Credentials Invalid", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(responseCode = "500", description = "Internet Server Error", content =
            @Content(schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @PostMapping("/v2")
    public ResponseEntity<Player> loginPlayer(@RequestBody @Valid CredentialV2 credentialV2){
        return ResponseEntity.ok(loginService.login(credentialV2));
    }
}
