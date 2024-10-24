package apimodels.appeals;

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
public class Items{

	@JsonProperty("hasMore")
	private boolean hasMore;

	@JsonProperty("items")
	private List<ItemsItem> items;

	public boolean isHasMore(){
		return hasMore;
	}

	public List<ItemsItem> getItems(){
		return items;
	}
}