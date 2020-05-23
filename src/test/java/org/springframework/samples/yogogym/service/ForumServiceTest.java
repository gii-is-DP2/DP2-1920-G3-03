package org.springframework.samples.yogogym.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Forum;
import org.springframework.samples.yogogym.model.Message;
import org.springframework.samples.yogogym.repository.ForumRepository;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ForumServiceTest {
	
	@Autowired
	private ForumRepository forumRepository;
	
	@Autowired
	protected ClientService clientService;

	private final String clientUsername = "client1";
	private final int guildId = 1;
	
	@Test
	void shouldFindAllForums(){
		Collection<Forum> allForums = this.forumRepository.findAllForums();
		
		//Check that we get some routine from the method
		Boolean notNull = allForums != null;
		
		//Check that the fetched routine has the same data that we introduced
		Boolean checkSize = allForums.size() == 3;
		
		assertTrue(notNull && checkSize);
	}
	
	@Test
	void shouldFindForumByGuildId()
	{
		Forum fToCompare = this.forumRepository.findAllForums().stream().filter(x->x.getGuild().getId() == guildId).findFirst().orElse(null);	
		Forum f = this.forumRepository.findForumByGuildId(guildId);
	
		//Check Params: Not Null
		Boolean sameForums = compareSame(f,fToCompare);		
		assertTrue(sameForums);
	}
	
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@ParameterizedTest
	@ValueSource(strings  = {"cotent0","content2","content3","content4","content5"})
	@Transactional
	public void shouldSaveForum(String input)
	{
		Forum f = this.forumRepository.findForumByGuildId(guildId);
		
		//Check messages size before saving
		int sizeMessagesBeforeSaving = f.getMessages().size();
		
		//Add messages
		Message m = createMessage(input,false,clientUsername);
		f.getMessages().add(m);
		this.forumRepository.save(f);
		
		//Check messages size after saving
		int sizeMessagesAfterSaving =  this.forumRepository.findForumByGuildId(guildId).getMessages().size();
		
		Boolean sizeIsBigger = sizeMessagesAfterSaving > sizeMessagesBeforeSaving;
		assertTrue(sizeIsBigger);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@Ignore
	public void shouldDeleteForum()
	{
		Forum f = this.forumRepository.findForumByGuildId(guildId);
		this.forumRepository.delete(f);
		
		Boolean doNotExist = this.forumRepository.findForumByGuildId(guildId) == null;
		
		assertTrue(doNotExist);
	}
	
	Boolean compareSame(Forum f1, Forum f2)
	{
		Boolean f1NotNull = f1 != null;
		Boolean f2NotNull = f2 != null;
		
		if(f1NotNull && f2NotNull)
		{
			Boolean guild =  f1.getGuild().equals(f2.getGuild());
			Boolean messages = f1.getMessages().equals(f2.getMessages());
			
			return guild && messages;
		}
		else
		{
			return false;
		}
	}
	
	Message createMessage(String content, Boolean isParent, String clientUsername)
	{
		Message res = new Message();
		
		res.setAnswers(new ArrayList<>());
		res.setContent(content);
		res.setEdited(false);
		res.setIsParent(isParent);
		res.setUser(this.clientService.findClientByUsername(clientUsername).getUser());
		
		return res;
	}
}
