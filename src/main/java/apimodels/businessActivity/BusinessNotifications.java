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
public class BusinessNotifications {

	@JsonProperty("ObjectsOfBusinessNotificationsPojo")
	private List<BusinessNotificationsItems> objectsOfBusinessNotificationsPojo;

}