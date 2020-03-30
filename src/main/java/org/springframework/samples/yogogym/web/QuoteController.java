package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Quote;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class QuoteController {

	// private final QuoteService quoteService;

	@Autowired
	public QuoteController() {
	
		// this.quoteService = quoteService;

	}

	
	@GetMapping("/mainMenu/quotes")
	public String MainMenuQuote(RestTemplate restTemplate, Model model) {

		Quote quote = restTemplate.getForObject("https://api.kanye.rest", Quote.class);

		model.addAttribute("quote", quote);
		
		return "/mainMenu/quotes/quote";
	 }

}