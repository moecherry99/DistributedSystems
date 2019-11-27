package ie.gmit.ds;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	// variables
    private int userId;
    private String userName;
    private String email;
    private String pw;


    public User() {
        // Jackson deserialisation
    }

    // constructor
    public User(int userId, String userName, String email, String pw) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.pw = pw;
    }

    // getters and setters
    @JsonProperty
    public String getPw() {
        return pw;
    }//pw

    @JsonProperty
    public String getEmail() {
        return email;
    }//email

    @JsonProperty
    public String getUserName() {
        return userName;
    }//email

    @JsonProperty
    public int getUserId() {
        return userId;
    }//email
}
