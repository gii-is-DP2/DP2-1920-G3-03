package org.springframework.samples.yogogym.service;

import java.util.Arrays;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.yogogym.model.Track;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class TrackService {

	private RestTemplate restTemplate;
	
	@Autowired
	public TrackService() {
		
	}
	
	@Transactional
	public Track getTrack() throws DataAccessException{
		
		String idTrack = "1MMhI6ISQt0UOVtU6DoAtE";
		Track track = new Track();
		
		try {
			HttpEntity<String> entity = setUpHeaders();
			ResponseEntity<Track> response = restTemplate.exchange("https://api.spotify.com/v1/tracks/"+idTrack, HttpMethod.GET, entity, Track.class);
			track.setName(response.getBody().getName());	
			track.setId(response.getBody().getId());
			track.setPreviewUrl(response.getBody().getPreviewUrl());
			track.setUri(response.getBody().getUri());
            return track;		
			
		} catch (Exception e) {
            return null;
		}
		
    }
    	private HttpEntity<String> setUpHeaders(){
    	String token = "BQDCwOvL443WoLzCK-CMxfE14ZuTIWVBUJUhnsq7y3yUhIqeMjbW5L_8ItpG7G--dzXNel2mPPEhpbRgTPEDVihvS3ogKoB_POtEu1GmuHBf-NK6xgK5ljvQU8gLR9yIsyqyIz49vjEFkHRXyUMu";
		
    	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.add("Authorization", "Bearer "+token );
		return new HttpEntity<String>("parameters", headers);
	}
}

