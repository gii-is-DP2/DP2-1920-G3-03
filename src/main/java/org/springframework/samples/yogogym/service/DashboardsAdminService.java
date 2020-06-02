package org.springframework.samples.yogogym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageClients;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesPercentageGuilds;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopClient;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopGuild;
import org.springframework.samples.yogogym.repository.DashboardsAdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardsAdminService {

	@Autowired
	private DashboardsAdminRepository dashboardRepository;

	/* Equipment control */

	@Transactional(readOnly = true)
	public Integer[] countEquipment(int month, int year) {
		return this.dashboardRepository.countEquipment(month, year);
	}

	@Transactional(readOnly = true)
	public String[] nameEquipment(int month, int year) {
		return this.dashboardRepository.nameEquipment(month, year);
	}

	
	// Dashboard Challenges
	
	@Transactional(readOnly = true)
	public int countChallengesOfMonthAndYear(int month, int year) {
		
		return this.dashboardRepository.countChallengesOfMonthAndYear(month, year);
	}
	
	@Transactional(readOnly = true)
	public int countCompletedInscriptionsOfMonthAndYear(int month, int year) {

		return this.dashboardRepository.countCompletedInscriptionsOfMonthAndYear(month, year);
	}
	
	@Transactional(readOnly = true)
	public String[] getChallengesNamesOfMonthAndYear(int month, int year) {
		
		return this.dashboardRepository.getChallengesNamesOfMonthAndYear(month, year);
	}
	
	@Cacheable("topPointClient")
	@Transactional(readOnly = true)
	public DashboardAdminChallengesTopClient getTopClient(int month, int year) {

		return this.dashboardRepository.getTopClient(month, year, PageRequest.of(0,1)).get(0);
	}
	
	@Cacheable("topPointGuild")
	@Transactional(readOnly = true)
	public DashboardAdminChallengesTopGuild getTopGuild(int month, int year) {

		return this.dashboardRepository.getTopGuild(month, year, PageRequest.of(0,1)).get(0);
	}
	
	@Cacheable("percentageClients")
	@Transactional(readOnly = true)
	public List<DashboardAdminChallengesPercentageClients> getPercentageClients(int month, int year) {
		
		return this.dashboardRepository.getPercentageClients(month, year);
	}
	
	@Cacheable("percentageGuilds")
	@Transactional(readOnly = true)
	public List<DashboardAdminChallengesPercentageGuilds> getPercentageGuilds(int month, int year) {
		
		return this.dashboardRepository.getPercentageGuilds(month, year);
	}
	
	
	
	// Dashboard General

	@Transactional(readOnly = true)
	public Integer countClients() {
		return this.dashboardRepository.countClients();
	}
	
	@Transactional(readOnly = true)
	public Integer countTrainers() {
		return this.dashboardRepository.countTrainers();
	}
	
	@Transactional(readOnly = true)
	public List<Integer> countClientsPerGuild() {
		return this.dashboardRepository.countClientsPerGuild();
	}

	
}
