package org.springframework.samples.yogogym.web.integration;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.yogogym.model.TokenMapper;
import org.springframework.samples.yogogym.web.PlaylistController;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;



import org.junit.jupiter.api.Test;

import lombok.extern.java.Log;

@Log
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