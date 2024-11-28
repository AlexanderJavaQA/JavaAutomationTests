package apimodels.controlObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Sort{

	@JsonProperty("unsorted")
	private boolean unsorted;

	@JsonProperty("sorted")
	private boolean sorted;

	@JsonProperty("empty")
	private boolean empty;

}