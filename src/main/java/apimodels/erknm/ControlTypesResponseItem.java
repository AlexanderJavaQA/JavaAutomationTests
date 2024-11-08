package apimodels.erknm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ControlTypesResponseItem{

	@JsonProperty("supervisionId")
	private String supervisionId;

	@JsonProperty("title")
	private String title;

}