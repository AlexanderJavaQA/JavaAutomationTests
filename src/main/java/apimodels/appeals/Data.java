package apimodels.appeals;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class Data{

	@JsonProperty("controlCode")
	private String controlCode;

	@JsonProperty("checkNumber")
	private String checkNumber;

	@JsonProperty("orgName")
	private String orgName;

	@JsonProperty("knoName")
	private String knoName;

	@JsonProperty("controlName")
	private String controlName;

	public String getControlCode(){
		return controlCode;
	}

	public String getCheckNumber(){
		return checkNumber;
	}

	public String getOrgName(){
		return orgName;
	}

	public String getKnoName(){
		return knoName;
	}

	public String getControlName(){
		return controlName;
	}
}