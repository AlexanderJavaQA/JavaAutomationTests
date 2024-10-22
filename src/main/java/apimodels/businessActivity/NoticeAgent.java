package apimodels.businessActivity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NoticeAgent{

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("middleName")
	private String middleName;

	@JsonProperty("email")
	private String email;

	@JsonProperty("position")
	private String position;

	@JsonProperty("comment")
	private String comment;

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getPhone(){
		return phone;
	}

	public String getMiddleName(){
		return middleName;
	}

	public String getEmail(){
		return email;
	}
}