package apimodels.controlObjects;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ControlObjectsAllResponse {

	@JsonProperty("registryObjectId")
	private String registryObjectId;

	@JsonProperty("objectId")
	private String objectId;

	@JsonProperty("controlType")
	private String controlType;

	@JsonProperty("riskCategory")
	private String riskCategory;

	@JsonProperty("address")
	private String address;

	@JsonProperty("objectType")
	private String objectType;

	@JsonProperty("objectSpecType")
	private String objectSpecType;

	@JsonProperty("objectSubspecType")
	private String objectSubspecType;

	@JsonProperty("controlOrganId")
	private String controlOrganId;

	@JsonProperty("objectName")
	private String objectName;

	@JsonProperty("controlOrganTitle")
	private String controlOrganTitle;

	@JsonProperty("controlOrganOGRN")
	private String controlOrganOGRN;

	@JsonProperty("total")
	private int total;

	@JsonProperty("pageNumber")
	private int pageNumber;

	@JsonProperty("pageSize")
	private int pageSize;

	@JsonProperty("items")
	private List<ItemsItem> items;

	public int getTotal(){
		return total;
	}

	public int getPageNumber(){
		return pageNumber;
	}

	public int getPageSize(){
		return pageSize;
	}

	public List<ItemsItem> getItems(){
		return items;
	}
}