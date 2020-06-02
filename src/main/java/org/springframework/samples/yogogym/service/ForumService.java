package org.springframework.samples.yogogym.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.yogogym.model.Forum;
import org.springframework.samples.yogogym.repository.ForumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ForumService {

	private ForumRepository forumRepository;

	@Autowired
	public ForumService(ForumRepository forumRepository) {
		this.forumRepository = forumRepository;
	}
	
	@Transactional(readOnly=true)
	public Collection<Forum> findAllForums() throws DataAccessException {
		return this.forumRepository.findAllForums();
	}
	
	@Transactional
	public void saveForum(Forum forum) throws DataAccessException {
		this.forumRepository.save(forum);
	}
	
	@Transactional
	public void deleteForum(Forum forum) throws DataAccessException {
		this.forumRepository.delete(forum);
	}
	
	@Transactional(readOnly=true)
	public int findForumIdByGuildId(int guildId) throws DataAccessException {
		return this.forumRepository.findForumIdByGuildId(guildId);
	}
	
	@Transactional(readOnly=true)
	public Forum findForumByGuildId(int guildId) throws DataAccessException {
		return this.forumRepository.findForumByGuildId(guildId);
	}

}
