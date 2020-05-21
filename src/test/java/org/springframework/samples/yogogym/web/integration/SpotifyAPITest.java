package org.springframework.samples.yogogym.web.integration;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import lombok.extern.java.Log;



import org.junit.jupiter.api.Test;

import lombok.extern.java.Log;

@Log
public class SpotifyAPITest {
	private String idTrack = "1MMhI6ISQt0UOVtU6DoAtE";
	private String token = "BQDCwOvL443WoLzCK-CMxfE14ZuTIWVBUJUhnsq7y3yUhIqeMjbW5L_8ItpG7G--dzXNel2mPPEhpbRgTPEDVihvS3ogKoB_POtEu1GmuHBf-NK6xgK5ljvQU8gLR9yIsyqyIz49vjEFkHRXyUMu";
    @Test
    public void ReturnTrack() {
    	given().auth().oauth2(token).
        when()
            .get("https://api.spotify.com/v1/tracks/"+idTrack)
        .then()
            .statusCode(200)
        .and()
            .assertThat()
                .body("name", equalTo("MONEY TILL I DIE"))
                .body("id", equalTo("1MMhI6ISQt0UOVtU6DoAtE"))
                .body("preview_url", equalTo("https://p.scdn.co/mp3-preview/d3277c921dadb67848c498974209c029b902944e?cid=774b29d4f13844c495f206cafdad9c86"))
                .body("uri",equalTo("spotify:track:1MMhI6ISQt0UOVtU6DoAtE"));
    }
}