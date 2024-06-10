package ar.edu.utn.frc.tup.lciii.dtos.play;

import ar.edu.utn.frc.tup.lciii.models.rps.ShapeHand;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayRpsDto {
	@NotNull
	@JsonProperty("shape_hand_player_1")
	private ShapeHand shapeHandPlayer1;

	@JsonProperty("shape_hand_player_2")
	private ShapeHand shapeHandPlayer2;
}
