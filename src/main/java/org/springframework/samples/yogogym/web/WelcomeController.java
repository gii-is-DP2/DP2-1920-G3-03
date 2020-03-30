package org.springframework.samples.yogogym.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Quote;
import org.springframework.samples.yogogym.model.User;
import org.springframework.samples.yogogym.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(RestTemplate restTemplate, Model model) {	    
		Quote quote = restTemplate.getForObject("https://api.kanye.rest", Quote.class);

		model.addAttribute("quote", quote);

	    return "welcome";
	  }
	 
}
