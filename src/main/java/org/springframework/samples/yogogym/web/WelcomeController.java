package org.springframework.samples.yogogym.web;

import org.springframework.samples.yogogym.model.Quote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(RestTemplate restTemplate, Model model) {	    
		  try {
			Quote quote = restTemplate.getForObject("https://api.kanye.rest", Quote.class);
			model.addAttribute("quote", quote);
		  } catch (Exception e) {
			  //TODO: handle exception
			Quote quote = new Quote();
			quote.setQuote("Just because our API fails doesnt mean you can fail too");
			model.addAttribute("quote", quote);
		  }	

	    return "welcome";
	  }
	 
}
