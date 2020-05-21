package org.springframework.samples.yogogym.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.yogogym.model.Track;
import org.springframework.samples.yogogym.service.TrackService;
import org.springframework.samples.yogogym.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TrackController {

	private final 	TrackService trackService;


	@Autowired
	public TrackController(TrackService trackService) {
		this.trackService = trackService;
	}
	
	@GetMapping("/mainMenu/tracks")
	public String getTrack(RestTemplate restTemplate, Model model) throws ResourceNotFoundException {
		String idTrack = "1MMhI6ISQt0UOVtU6DoAtE";

		Track track = new Track();
		
		try {

			HttpEntity<String> entity = setUpHeaders();
			ResponseEntity<Track> response = restTemplate.exchange("https://api.spotify.com/v1/tracks/"+idTrack, HttpMethod.GET, entity, Track.class);
			track.setName(response.getBody().getName());	
			model.addAttribute("track", track);
			model.addAttribute("apiFunctional", true);

			
		} catch (Exception e) {
			
			track.setName("Just because our API fails doesnt mean you can fail too");
			model.addAttribute("track", track);
			model.addAttribute("apiFunctional", false);

		}


		return "mainMenu/tracks/track";
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