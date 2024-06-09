package ar.edu.utn.frc.tup.lciii.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNameIdentity extends Identity {
    @Schema(title = "userName to logged in",
            description = "The player userName provided to logged in",
            example = "myUserName",
            nullable = false)
    @NotNull(message = "userName cant be null")
    @JsonProperty("user_name")
    private String userName;
}
