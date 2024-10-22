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
public class ItemsItem{

	@JsonProperty("address")
	private String address;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	public String getAddress(){
		return address;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}
}