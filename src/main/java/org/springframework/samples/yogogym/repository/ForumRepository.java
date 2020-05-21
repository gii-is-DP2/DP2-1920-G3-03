package org.springframework.samples.yogogym.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Forum;

public interface ForumRepository extends  CrudRepository<Forum, String>{
	
	@Query("SELECT forum.id FROM Forum forum WHERE forum.guild.id=:guildId")
	public int findForumIdByGuildId(@Param("guildId")int guildId);
	
	@Query("SELECT forum FROM Forum forum WHERE forum.guild.id=:guildId")
	public Forum findForumByGuildId(@Param("guildId")int guildId);
	
}
	
