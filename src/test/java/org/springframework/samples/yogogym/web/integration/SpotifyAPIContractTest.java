package org.springframework.samples.yogogym.web.integration;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import lombok.extern.java.Log;



import org.junit.jupiter.api.Test;

import lombok.extern.java.Log;

//@Log
//public class SpotifyAPIContractTest {
//    private String idTrack = "1MMhI6ISQt0UOVtU6DoAtE";
//    private String idPlaylist = "37i9dQZF1DX2apWzyECwyZ";
//    
//	private String token = "BQAYUOINkwRq5xESJOMpzXn7V-QQux87up1FQX-Cl1WausiGn7zhxnS_aEQnrvV9Z9P-av42CsJWd4do3BTge5NppBi3_OyGQfGnEgfQusM8VZuHL6ibImtY5D9b4UJXmDouRweZ825LpIXeWqiu";
//    @Test
//    public void returnTrack() {
//    	given().auth().oauth2(token).
//        when()
//            .get("https://api.spotify.com/v1/tracks/"+idTrack)
//        .then()
//            .statusCode(200)
//        .and()
//            .assertThat()
//                .body("name", equalTo("MONEY TILL I DIE"))
//                .body("id", equalTo("1MMhI6ISQt0UOVtU6DoAtE"))
//                .body("preview_url", equalTo("https://p.scdn.co/mp3-preview/d3277c921dadb67848c498974209c029b902944e?cid=774b29d4f13844c495f206cafdad9c86"))
//                .body("uri",equalTo("spotify:track:1MMhI6ISQt0UOVtU6DoAtE"));
//    }
//    @Test
//    public void returnPlaylist() {
//    	given().auth().oauth2(token).
//        when()
//            .get("https://api.spotify.com/v1/playlists/"+idPlaylist)
//        .then()
//            .statusCode(200)
//        .and()
//            .assertThat()
//                .body("name", equalTo("This Is Bad Bunny"))
//                .body("uri", equalTo("spotify:playlist:37i9dQZF1DX2apWzyECwyZ"))
//                .body("tracks", notNullValue());
//               
//    }
//}