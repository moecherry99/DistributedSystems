package ie.gmit.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserApiResource {

	private HashMap<Integer, User> userMap = new HashMap<>();

	public UserApiResource() {
		// for showing if localhost:9000 displays JSON data
		User test = new User(1, "Joe", "Joe@joemail.com", "JoeTheMan");
		userMap.put(test.getUserId(), test);
	}

	//Gets users logged in
	@GET
	public List<User> getUsers() {

		return new ArrayList<User>(userMap.values());
	}

	// gets your user specifically
	@Path("{userId}")
	@GET
	public User getUserById(@PathParam("userId") int userId) {
		return userMap.get(userId);
	}

	// add new (post)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(User newUser) {
		userMap.put(newUser.getUserId(), newUser);

		String response = "New User Id : " + newUser.getUserId();
		return Response.status(200).entity(response).build();
	}

	// Update (put)
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(User user) {
		userMap.put(user.getUserId(), user);

		String response = "User Id : " + user.getUserId();
		return Response.status(200).entity(response).build();
	}

	// Delete
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(User user) {
		userMap.remove(user.getUserId());
		String response = "Delete user with Id : " + user.getUserId();
		return Response.status(200).entity(response).build();
	}

	// Login (post)
	@Path("/login")
	@POST
	public Response login(UserLogin login) {
		if ((userMap.get(login.getId()) != null)
				&& (userMap.get(login.getId()).getPw().equals(login.getPw()))) {
			String response = "Logged in! Welcome user : " + login.getId();
			return Response.status(200).entity(response).build();
		}
		// Fails 
		else {
			String response = "Login Failed";
			return Response.status(400).entity(response).build();
		}
	}
}