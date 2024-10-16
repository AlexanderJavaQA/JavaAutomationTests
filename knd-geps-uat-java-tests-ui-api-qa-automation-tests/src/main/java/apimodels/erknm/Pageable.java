package apimodels.erknm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Pageable{

	@JsonProperty("paged")
	private boolean paged;

	@JsonProperty("pageNumber")
	private int pageNumber;

	@JsonProperty("offset")
	private int offset;

	@JsonProperty("pageSize")
	private int pageSize;

	@JsonProperty("unpaged")
	private boolean unpaged;

	@JsonProperty("sort")
	private Sort sort;

}