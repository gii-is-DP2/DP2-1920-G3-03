package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.model.Quote;
import org.springframework.samples.yogogym.service.QuoteService;
import org.springframework.samples.yogogym.service.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class QuoteController {

	@Autowired
	QuoteService quoteService;

	
	@GetMapping("/mainMenu/quotes")
	public Quote getQuote() throws ResourceNotFoundException {

		Quote quote = quoteService.getQuote();
		if(quote==null)
		throw new ResourceNotFoundException("No quote event found");
		
		return quote; 
	 }
	
	 
}