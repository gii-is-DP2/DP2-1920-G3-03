package org.springframework.samples.yogogym.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.yogogym.model.Playlist;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PlaylistControllerTest {

		private static final String testClientUsername1 = "client1";
		private static final Integer testTrainingId1 = 1;
		
		@Autowired
		private MockMvc mockMvc;
	
		private String idPlaylist = "37i9dQZF1DX2apWzyECwyZ";
	
		@Autowired
	    private TestRestTemplate restTemplate;

//		@WithMockUser()
//		@Test
//	    public void shouldFindPlaylist() throws Exception{
//			mockMvc.perform(get("/client/{clientUsername}/trainings/{trainingId}/playlist", testClientUsername1,testTrainingId1))
//			.andExpect(status().is3xxRedirection())
//			.andExpect(view().name("red"));
//		
//	    }
//	    
//	    @Test
//	    public void shouldNotFindPlaylist() {
//	    	
//	    }
//	    
//	    @Test
//	    public void shouldEmptyPlaylist() {
//	    	
//	    }
//	
}
