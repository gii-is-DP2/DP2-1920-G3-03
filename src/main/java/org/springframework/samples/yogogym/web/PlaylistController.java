package org.springframework.samples.yogogym.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.yogogym.model.Playlist;
import org.springframework.samples.yogogym.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class PlaylistController {


	@Autowired
	public PlaylistController() {
	}
	
	@GetMapping("/mainMenu/playlists")
	public String getPlaylist(RestTemplate restTemplate, Model model) throws ResourceNotFoundException {
		String idPlaylist = "37i9dQZF1DX2apWzyECwyZ";

		Playlist playlist = new Playlist();
		
		try {

			HttpEntity<String> entity = setUpHeaders();
			ResponseEntity<Playlist> response = restTemplate.exchange("https://api.spotify.com/v1/playlists/"+idPlaylist, HttpMethod.GET, entity, Playlist.class);
			playlist.setName(response.getBody().getName());
			playlist.setUri(response.getBody().getUri());	
			playlist.setTracks(response.getBody().getTracks());
			model.addAttribute("playlist", playlist);

			model.addAttribute("apiFunctional", true);

			
		} catch (Exception e) {
			
			playlist.setName("Just because our API fails doesnt mean you can fail too");
			model.addAttribute("playlist", playlist);
			model.addAttribute("apiFunctional", false);

		}


		return "mainMenu/playlists/playlist";
	 }

	 private HttpEntity<String> setUpHeaders(){
    	String token = "BQAYUOINkwRq5xESJOMpzXn7V-QQux87up1FQX-Cl1WausiGn7zhxnS_aEQnrvV9Z9P-av42CsJWd4do3BTge5NppBi3_OyGQfGnEgfQusM8VZuHL6ibImtY5D9b4UJXmDouRweZ825LpIXeWqiu";
		
    	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.add("Authorization", "Bearer "+token );
		return new HttpEntity<String>("parameters", headers);
	}
	 
}