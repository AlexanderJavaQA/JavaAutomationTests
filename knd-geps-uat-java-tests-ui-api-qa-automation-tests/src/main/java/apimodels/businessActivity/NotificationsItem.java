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
public class NotificationsItem{

	@JsonProperty("activityStartDate")
	private String activityStartDate;

	@JsonProperty("terminatedDate")
	private String terminatedDate;

	@JsonProperty("noticeEpguDateTime")
	private String noticeEpguDateTime;

	@JsonProperty("noticeAgent")
	private NoticeAgent noticeAgent;

	@JsonProperty("noticeId")
	private int noticeId;

	@JsonProperty("registryObjectId")
	private int registryObjectId;

	@JsonProperty("activityId")
	private String activityId;

	@JsonProperty("activityTitle")
	private String activityTitle;

	@JsonProperty("serviceTitle")
	private String serviceTitle;

	@JsonProperty("controlOrganTitle")
	private String controlOrganTitle;

	@JsonProperty("noticeVersionId")
	private int noticeVersionId;

	@JsonProperty("controlOrganId")
	private String controlOrganId;

	@JsonProperty("serviceId")
	private String serviceId;

	@JsonProperty("okveds")
	private List<String> okveds;

	@JsonProperty("terminated")
	private boolean terminated;

	@JsonProperty("noticeNumber")
	private String noticeNumber;


}