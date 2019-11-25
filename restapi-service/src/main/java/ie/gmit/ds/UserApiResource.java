package ie.gmit.ds;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserApiResource {
    List<User> users = Arrays.asList(new User(001, "Joe", "Joe@Joemail.com", "Joemail1"));


    @GET
    public List<User> getArtists() {
        return users;
    }
}
