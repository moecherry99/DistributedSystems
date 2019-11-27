package ie.gmit.ds;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

// User Login
public class UserLogin {
	@NotBlank
	private int userId;
	@NotBlank
	private String userPw;
	
	public UserLogin(int id, String password) {
		this.userId = id;
		this.userPw = password;
	}

	public UserLogin() {
	}
	
	@JsonProperty
	public int getId() {
		return userId;
	}

	@JsonProperty
	public String getPw() {
		return userPw;
	}
}