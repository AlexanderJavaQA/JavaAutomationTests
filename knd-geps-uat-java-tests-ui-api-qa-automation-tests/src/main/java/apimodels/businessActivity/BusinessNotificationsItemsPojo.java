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
public class BusinessNotificationsItemsPojo {

	@JsonProperty("activityId")
	private String activityId;

	@JsonProperty("activityTitle")
	private String activityTitle;

	@JsonProperty("notifications")
	private List<NotificationsItem> notifications;

	public String getActivityId(){
		return activityId;
	}

	public String getActivityTitle(){
		return activityTitle;
	}

	public List<NotificationsItem> getNotifications(){
		return notifications;
	}
}