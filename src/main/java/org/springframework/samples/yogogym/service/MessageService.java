package org.springframework.samples.yogogym.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Message;
import org.springframework.samples.yogogym.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
public class MessageService {

	private MessageRepository messageRepository;

	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	@Transactional(readOnly=true)
	public Collection<Message> findAllMessages() throws DataAccessException {
		return Lists.newArrayList(this.messageRepository.findAll());
	}

	@Transactional(readOnly=true)
	public Collection<Message> findAllForumParentMessages(int forumId) throws DataAccessException {

		Collection<Message> messages = (Collection<Message>) this.messageRepository.findMessagesFromForumId(forumId);

		Collection<Message> res = new ArrayList<>();

		for (Message m : messages) {
			if (m.getIsParent()) {
				res.add(m);
			}
		}

		Comparator<Message> messageComparator = Comparator.comparing(Message::getCreatedAt).reversed();

		res = res.stream().sorted(messageComparator).collect(Collectors.toList());
		
		return res;
	}

	@Transactional(readOnly=true)
	public Message findMessageFromId(int messageId) throws DataAccessException {
		return this.messageRepository.findMessageById(messageId);
	}

	@Transactional
	public void saveMessage(Message message) throws DataAccessException {
		try {
			this.messageRepository.save(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
