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

	public String getObjectSpecType(){
		return objectSpecType;
	}

	public String getRiskCategory(){
		return riskCategory;
	}

	public String getAddress(){
		return address;
	}

	public String getControlType(){
		return controlType;
	}

	public String getControlOrganTitle(){
		return controlOrganTitle;
	}

	public String getObjectSubspecType(){
		return objectSubspecType;
	}

	public String getControlOrganId(){
		return controlOrganId;
	}

	public String getObjectName(){
		return objectName;
	}

	public String getRegistryObjectId(){
		return registryObjectId;
	}

	public String getObjectId(){
		return objectId;
	}

	public String getObjectType(){
		return objectType;
	}

	public String getControlOrganOGRN(){
		return controlOrganOGRN;
	}
}