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
public class ContentItem{

	@JsonProperty("code")
	private String code;

	@JsonProperty("id")
	private String id;

	@JsonProperty("title")
	private String title;

}