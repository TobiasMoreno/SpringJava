package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
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
    @GetMapping("/{id}")
    public ResponseEntity<Player>getById(@PathVariable long id){

    return ResponseEntity.ok(playerService.getPlayerByid(id));
    }

    @PostMapping()
    public ResponseEntity<Player> savePlayer(@RequestBody @Valid Player player){

        Player newPlayer = playerService.savePlayer(player);

        if(newPlayer == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "El usuario o el email , ya existen");
        }

        return ResponseEntity.ok(newPlayer);
    }
}
