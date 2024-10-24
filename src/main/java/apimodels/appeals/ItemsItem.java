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

	public String getDate(){
		return date;
	}

	public Data getData(){
		return data;
	}

	public boolean isUnread(){
		return unread;
	}

	public String getOt(){
		return ot;
	}

	public boolean isHasQuiz(){
		return hasQuiz;
	}

	public boolean isArchive(){
		return archive;
	}

	public boolean isChiefOnly(){
		return chiefOnly;
	}

	public long getOwnerId(){
		return ownerId;
	}

	public String getTitle(){
		return title;
	}

	public long getUserId(){
		return userId;
	}

	public String getArchiveLevel(){
		return archiveLevel;
	}

	public String getSubTitle(){
		return subTitle;
	}

	public boolean isHiddenEvents(){
		return hiddenEvents;
	}

	public String getFeedType(){
		return feedType;
	}

	public List<Object> getPowerMnemonics(){
		return powerMnemonics;
	}

	public long getId(){
		return id;
	}

	public String getExtId(){
		return extId;
	}

	public String getStatus(){
		return status;
	}
}