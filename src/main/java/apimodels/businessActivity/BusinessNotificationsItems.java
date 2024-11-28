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
public class BusinessNotificationsItems {

	@JsonProperty("activityId")
	private String activityId;

	@JsonProperty("activityTitle")
	private String activityTitle;

	@JsonProperty("notifications")
	private List<NotificationsItem> notifications;

}