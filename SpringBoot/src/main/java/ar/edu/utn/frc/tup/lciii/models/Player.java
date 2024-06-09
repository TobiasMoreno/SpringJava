package ar.edu.utn.frc.tup.lciii.models;

import ar.edu.utn.frc.tup.lciii.utils.validation.password.ValidPassword;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Schema(title = "Player ID", description = "The player ID", example = "1")
    private Long id;

    @Schema(title = "Player user name", description = "The player userName", example = "TobiasExample", nullable = false)
    @NotNull(message = "userName cant be null")
    private String userName;

    @Schema(title = "Player ID", description = "The player Password", example = "Password#03", nullable = false)
    @NotNull(message = "password cant be null")
    @ValidPassword
    private String password;

    @Schema(title = "Player Email", description = "The player Email", example = "email@email.com", nullable = false)
    @NotNull(message = "Email cant be null")
    @Email(message = "El email tiene que ser valido")
    private String email;

    @Schema(title = "Player Avatar URL", description = "The player Avatar", example = "http://localhost:8080/avatars/myUsername", nullable = true)
    private String avatar;

    @Schema(title = "Player Last Login", description = "The player Last Login", example = "08-05-2024 23:03:45", nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastLoginDate;
}
