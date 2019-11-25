package ie.gmit.ds;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String userId;
    private String artistName;
    private String artistGenre;
    private int albumsRecorded;


    public User() {
        // Needed for Jackson deserialisation
    }

    public User(String userId, String artistName, String artistGenre, int albumsRecorded) {
        this.userId = userId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
        this.albumsRecorded = albumsRecorded;
    }

    @JsonProperty
    public String getArtistName() {
        return artistName;
    }

    @JsonProperty
    public String getArtistGenre() {
        return artistGenre;
    }

    @JsonProperty
    public int getAlbumsRecorded() {
        return albumsRecorded;
    }

    @JsonProperty
    public String getUserId() {
        return userId;
    }
}
