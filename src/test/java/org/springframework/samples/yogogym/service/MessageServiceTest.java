package org.springframework.samples.yogogym.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.yogogym.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MessageServiceTest {
	
	@Autowired
	private MessageService messageService;
		
	@Autowired
	protected ClientService clientService;

	private final String clientUsername = "client1";
	private final int forumId = 1;
	private final int messageId = 1;
	
	@Test
	void shouldFindAllMessages(){
		Collection<Message> allMessages = this.messageService.findAllMessages();
		
		//Check not null
		Boolean notNull = allMessages != null;
		
		//Check size
		Boolean checkSize = allMessages.size() == 7;
		
		assertTrue(notNull && checkSize);
	}
	
	@Test
	void shouldFindAllForumParentMessages(){
		Collection<Message> allParentMessages = this.messageService.findAllForumParentMessages(forumId);
		
		//Check that we get some routine from the method
		Boolean notNull = allParentMessages != null;
		
		//Check that the fetched routine has the same data that we introduced
		Boolean checkSize = allParentMessages.size() == 3;
		
		assertTrue(notNull && checkSize);
	}
	
	@Test
	void shouldFindMesageFromId(){
		
		Message mToCompare = this.messageService.findAllMessages().stream().filter(x->x.getId().equals(messageId)).findFirst().orElse(null);
		Message m = this.messageService.findMessageFromId(messageId);
		
		//Check Not Null
		Boolean notNullMtoCompare = mToCompare != null;
		Boolean NotNullM = m != null;
		
		//Check Param
		Boolean equals = m.equals(mToCompare);
				
		assertTrue(NotNullM && notNullMtoCompare && equals);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@ParameterizedTest
	@ValueSource(strings  = {"cotent0","content2","content3","content4","content5"})
	@Transactional
	void shouldSaveMessage(String input)
	{
		//All Messages
		Collection<Message> allMessages = this.messageService.findAllMessages();
		
		//Check messages size
		int beforeAdding = allMessages.size();
		
		//Create message
		Message m = createMessage(input, true, clientUsername);
		
		try
		{			
			this.messageService.saveMessage(m);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//Check size after adding
		int afterAdding = this.messageService.findAllMessages().size();
		
		Boolean isGreater = afterAdding > beforeAdding;
		
		assertTrue(isGreater);
	}
	
	@DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
	@ParameterizedTest
	@ValueSource(strings  = {"cotent0","content2","content3","content4","content5"})
	@Transactional
	void shouldSaveAnswerToMessage(String input)
	{
		//Parent Message
		Message m = this.messageService.findAllMessages().stream().filter(x->x.getIsParent()==true).findFirst().orElse(null);
		
		//Check that a parent message exist
		assert(m!=null);
		
		int beforeAdding = m.getAnswers().size();
		int mId = m.getId();		
				
		//Answer to message
		Message answer = createMessage(input, false,clientUsername);
		m.getAnswers().add(answer);
		
		try
		{			
			this.messageService.saveMessage(m);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//Fetch parent message answers size
		int afterAdding = this.messageService.findMessageFromId(mId).getAnswers().size();
	
		//Check that answer size has incremented
		Boolean isGreater = afterAdding > beforeAdding;
		
		assertTrue(isGreater);
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
