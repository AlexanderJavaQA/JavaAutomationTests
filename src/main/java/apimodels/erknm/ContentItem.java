package apimodels.erknm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentItem{

	@JsonProperty("knoId")
	private String knoId;

	@JsonProperty("supervisionId")
	private String supervisionId;

	@JsonProperty("inspectionId")
	private String inspectionId;

	@JsonProperty("supervisionTitle")
	private String supervisionTitle;

	@JsonProperty("knoTitle")
	private String knoTitle;

	@JsonProperty("regionTitle")
	private String regionTitle;

	@JsonProperty("knoBranchId")
	private String knoBranchId;

	@JsonProperty("knoBranchTitle")
	private String knoBranchTitle;

}