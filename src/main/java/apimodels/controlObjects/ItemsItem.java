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
public class ItemsItem{

	@JsonProperty("objectSpecType")
	private String objectSpecType;

	@JsonProperty("riskCategory")
	private String riskCategory;

	@JsonProperty("address")
	private String address;

	@JsonProperty("controlType")
	private String controlType;

	@JsonProperty("controlOrganTitle")
	private String controlOrganTitle;

	@JsonProperty("objectSubspecType")
	private String objectSubspecType;

	@JsonProperty("controlOrganId")
	private String controlOrganId;

	@JsonProperty("objectName")
	private String objectName;

	@JsonProperty("registryObjectId")
	private String registryObjectId;

	@JsonProperty("objectId")
	private String objectId;

	@JsonProperty("objectType")
	private String objectType;

	@JsonProperty("controlOrganOGRN")
	private String controlOrganOGRN;

}