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

	public String getKnoId(){
		return knoId;
	}

	public String getSupervisionId(){
		return supervisionId;
	}

	public String getInspectionId(){
		return inspectionId;
	}

	public String getSupervisionTitle(){
		return supervisionTitle;
	}

	public String getKnoTitle(){
		return knoTitle;
	}

	public String getRegionTitle(){
		return regionTitle;
	}

	public String getKnoBranchId(){
		return knoBranchId;
	}

	public String getKnoBranchTitle(){
		return knoBranchTitle;
	}
}