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
    List<User> artists = Arrays.asList(new User("1", "The GZA", "HipHop", 2));


    @GET
    public List<User> getArtists() {
        return artists;
    }
}
