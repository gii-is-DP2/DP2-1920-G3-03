package org.springframework.samples.yogogym.web.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.springframework.samples.yogogym.web.PlaylistController;

public class SpotifyAPIContractTest {
    
    private String idPlaylist = "37i9dQZF1DX2apWzyECwyZ";

	private String token = PlaylistController.getToken().getAccessToken();

    @Test
    public void returnPlaylist() {
    	given().auth().oauth2(token).
        when()
            .get("https://api.spotify.com/v1/playlists/"+idPlaylist)
        .then()
            .statusCode(200)
        .and()
            .assertThat()
                .body("name", equalTo("This Is Bad Bunny"))
                .body("uri", equalTo("spotify:playlist:37i9dQZF1DX2apWzyECwyZ"))
                .body("tracks", notNullValue());
               
    }
    
    @Test
    public void returnErrorPlaylist() {
    	
    	given().auth().oauth2(token).
        when()
            .get("https://api.spotify.com/v1/playlists/")
        .then()
            .statusCode(404);
    }
       
}