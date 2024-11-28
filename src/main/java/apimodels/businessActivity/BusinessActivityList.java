package apimodels.businessActivity;

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
public class BusinessActivityList {

	@JsonProperty("total")
	private int total;

	@JsonProperty("pageNumber")
	private int pageNumber;

	@JsonProperty("pageSize")
	private int pageSize;

	@JsonProperty("items")
	private List<ItemsItem> items;

}