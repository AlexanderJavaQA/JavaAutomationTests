package apimodels.businessActivity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessActivityCard {

	@JsonProperty("regionCode")
	private String regionCode;

	@JsonProperty("fiasCodes")
	private String fiasCodes;

	@JsonProperty("total")
	private String total;

	@JsonProperty("pageSize")
	private String pageSize;

	@JsonProperty("address")
	private String address;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("regionTitle")
	private String regionTitle;

	@JsonProperty("pageNumber")
	private String pageNumber;

}