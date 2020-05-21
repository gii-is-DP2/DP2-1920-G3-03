package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Example;
import org.springframework.samples.yogogym.model.Quote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TracksController {
	
	private final String CLIENT_ID = "956b8ae3e4b246b6a82c4a2c5ce6e4ac";
	private final String CLIENT_SECRET = "d920a28ac958459eb354f82226dbaf16";
	@Autowired
	public TracksController() {
		
	}
	
	
	
	@GetMapping("/mainMenu/tracks")
	public String MainMenuTracks(RestTemplate restTemplate, Model model) {

		Example track = restTemplate.getForObject("https://api.spotify.com/v1/tracks/{1MMhI6ISQt0UOVtU6DoAtE}", Example.class);

		model.addAttribute("track", track);
		
		return "/mainMenu/tracks/track";
	 }
}
