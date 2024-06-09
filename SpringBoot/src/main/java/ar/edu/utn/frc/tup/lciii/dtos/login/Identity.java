package ar.edu.utn.frc.tup.lciii.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Declara  la info de un tipo de JSON, en este caso de IdentityType, en la clase en la anotacion @JsonProperty le tengo que poner
// el mismo nombre
@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, property = "identity_type", include = JsonTypeInfo.As.EXISTING_PROPERTY, visible = true)
@JsonSubTypes({
//Con estas anotaciones hace que identity al ser abstracta y no poder crearse a si misma, se cree segun el tipo de IdentityType
//Entonces si el name es = "USERNAME"( es del enum IdentityType) crea la clase UserName, lo mismo con Email
        @JsonSubTypes.Type(value = UserNameIdentity.class, name = "USERNAME"),
        @JsonSubTypes.Type(value = EmailIdentity.class, name = "EMAIL")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Identity {
    @Schema(title = "Type of identity used to logged in",
            description = "The Type of identity provided to logged in",
            example = "USERNAME OR EMAIL",
            nullable = false)
    @NotNull(message = "identity_type cant be null")
    @JsonProperty("identity_type")
    private IdentityType identityType;
}
