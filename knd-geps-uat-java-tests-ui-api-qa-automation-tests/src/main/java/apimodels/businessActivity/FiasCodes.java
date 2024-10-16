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
public class FiasCodes{

	@JsonProperty("cityFiasId")
	private String cityFiasId;

	@JsonProperty("fiasId")
	private String fiasId;

	@JsonProperty("areaFiasId")
	private String areaFiasId;

	@JsonProperty("cityDistrictFiasId")
	private String cityDistrictFiasId;

	@JsonProperty("flatFiasId")
	private String flatFiasId;

	@JsonProperty("streetFiasId")
	private String streetFiasId;

	@JsonProperty("regionFiasId")
	private String regionFiasId;

	@JsonProperty("settlementFiasId")
	private String settlementFiasId;

	@JsonProperty("steadFiasId")
	private String steadFiasId;

	@JsonProperty("houseFiasId")
	private String houseFiasId;

	public String getCityFiasId(){
		return cityFiasId;
	}

	public String getFiasId(){
		return fiasId;
	}

	public String getAreaFiasId(){
		return areaFiasId;
	}

	public String getCityDistrictFiasId(){
		return cityDistrictFiasId;
	}

	public String getFlatFiasId(){
		return flatFiasId;
	}

	public String getStreetFiasId(){
		return streetFiasId;
	}

	public String getRegionFiasId(){
		return regionFiasId;
	}

	public String getSettlementFiasId(){
		return settlementFiasId;
	}

	public String getSteadFiasId(){
		return steadFiasId;
	}

	public String getHouseFiasId(){
		return houseFiasId;
	}
}