package org.springframework.samples.yogogym.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.yogogym.model.Client;
import org.springframework.samples.yogogym.model.Guild;

public interface GuildRepository extends CrudRepository<Guild, String>{

	@Query("select g from Guild g where g.id=:id")
	public Guild findGuildById(@Param("id")int id);
	
	@Query("select g from Guild g")
	Collection<Guild> findAllGuilds();
	
	@Query("select c from Client c where c.guild=:guild")
	Collection<Client> findClientsByGuild(Guild guild);
}
