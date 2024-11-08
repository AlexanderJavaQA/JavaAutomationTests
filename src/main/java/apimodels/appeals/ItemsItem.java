package apimodels.appeals;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class ItemsItem{

	@JsonProperty("date")
	private String date;

	@JsonProperty("data")
	private Data data;

	@JsonProperty("unread")
	private boolean unread;

	@JsonProperty("ot")
	private String ot;

	@JsonProperty("branchId")
	private String branchId;

	@JsonProperty("hasQuiz")
	private boolean hasQuiz;

	@JsonProperty("archive")
	private boolean archive;

	@JsonProperty("chiefOnly")
	private boolean chiefOnly;

	@JsonProperty("ownerId")
	private long ownerId;

	@JsonProperty("title")
	private String title;

	@JsonProperty("userId")
	private long userId;

	@JsonProperty("archiveLevel")
	private String archiveLevel;

	@JsonProperty("subTitle")
	private String subTitle;

	@JsonProperty("hiddenEvents")
	private boolean hiddenEvents;

	@JsonProperty("feedType")
	private String feedType;

	@JsonProperty("powerMnemonics")
	private List<Object> powerMnemonics;

	@JsonProperty("id")
	private long id;

	@JsonProperty("extId")
	private String extId;

	@JsonProperty("status")
	private String status;

}