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
public class BusinessActivityListPojo {

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