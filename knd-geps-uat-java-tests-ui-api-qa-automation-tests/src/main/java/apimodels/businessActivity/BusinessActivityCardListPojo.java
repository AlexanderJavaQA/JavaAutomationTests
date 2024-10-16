package apimodels.businessActivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BusinessActivityCardListPojo {

	@JsonProperty("regionCode")
	private String regionCode;

	@JsonProperty("address")
	private String address;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("regionTitle")
	private String regionTitle;

	@JsonProperty("fiasCodes")
	private FiasCodes fiasCodes;

	public String getRegionCode(){
		return regionCode;
	}

	public String getAddress(){
		return address;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getRegionTitle(){
		return regionTitle;
	}

	public FiasCodes getFiasCodes(){
		return fiasCodes;
	}
}