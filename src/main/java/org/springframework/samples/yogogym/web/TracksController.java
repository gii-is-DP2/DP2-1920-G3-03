package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Track;
import org.springframework.samples.yogogym.service.TrackService;
import org.springframework.samples.yogogym.service.exceptions.ResourceNotFoundException;
import org.springframework.samples.yogogym.model.Quote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TracksController {
	
	@Autowired
	TrackService trackService;
	
	@GetMapping("/mainMenu/tracks")
	public Track getTrack() throws ResourceNotFoundException{

		Track track = trackService.getTrack();

		if(track==null)
		throw new ResourceNotFoundException("No track found");
		
		return track;
	 }
}
