package org.springframework.samples.yogogym.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Message;

public interface MessageRepository extends  CrudRepository<Message, String>{
	
	@Query("SELECT forum.messages FROM Forum forum WHERE forum.id=:forumId")
	public Collection<Message> findMessagesFromForumId(@Param("forumId")int forumId);
	
	@Query("SELECT message FROM Message message WHERE message.id=:id")
	public Message findMessageById(@Param("id")int id);
	
	@Query("SELECT forum.id FROM Forum forum WHERE forum.guild.id=:guildId")
	public int findForumIdByGuildId(@Param("guildId")int guildId);
	
}
	
