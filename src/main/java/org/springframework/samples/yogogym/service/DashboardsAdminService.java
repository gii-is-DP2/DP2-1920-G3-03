package org.springframework.samples.yogogym.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.samples.yogogym.model.Challenge;
import org.springframework.samples.yogogym.model.Inscription;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopClient;
import org.springframework.samples.yogogym.projections.DashboardAdminChallengesTopGuild;
import org.springframework.samples.yogogym.repository.DashboardsAdminRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardsAdminService {

	@Autowired
	private DashboardsAdminRepository dashboardRepository;
	
	private final Calendar nowFinal = Calendar.getInstance();

	/* Equipment control */

	public List<Integer> countEquipment(Integer days) {
		Calendar now = (Calendar) nowFinal.clone();
		now.add(Calendar.DAY_OF_MONTH, -days);
		Date d2 = now.getTime();
		return this.dashboardRepository.countEquipment(d2);
	}

	public List<String> nameEquipment(Integer days) {
		Calendar now = (Calendar) nowFinal.clone();
		now.add(Calendar.DAY_OF_MONTH, -days);
		Date d2 = now.getTime();
		return this.dashboardRepository.nameEquipment(d2);
	}

	public Collection<Challenge> getChallengesOfMonthAndYear(int month, int year) {

		return this.dashboardRepository.findChallengesByMonthAndYear(month,year);
	}
	
	// LO NUEVO
	
	public int countCompletedInscriptionsOfMonthAndYear(int month, int year) {

		return this.dashboardRepository.countCompletedInscriptionsOfMonthAndYear(month, year);
	}
	
	public DashboardAdminChallengesTopClient getTopClient(int month, int year) {

		return this.dashboardRepository.findTopClient(month, year, PageRequest.of(0,1)).get(0);
	}
	
	public DashboardAdminChallengesTopGuild getTopGuild(int month, int year) {

		return this.dashboardRepository.findTopGuild(month, year, PageRequest.of(0,1)).get(0);
	}
	
	public String[] getChallengesNames(int month, int year) {
		
		return this.dashboardRepository.getChallengesNames(month, year);
	}
	
	public Double[] getPercentageClients(int month, int year) {
		
		return this.dashboardRepository.getPercentageClients(month, year);
	}
	
	public Double[] getPercentageGuilds(int month, int year) {
		
		return this.dashboardRepository.getPercentageGuilds(month,year);
	}
	
	// YA NO LO NUEVO

	public List<Inscription> findCompletedInscriptionsThisMonth(int month) {
		
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		Integer year = cal.get(Calendar.YEAR);
		
		return dashboardRepository.findCompletedInscriptionsByMonthAndYear(month,year);
	}

	public Integer countClients() {
		return this.dashboardRepository.countClients();
	}
	public Integer countTrainers() {
		return this.dashboardRepository.countTrainers();
	}
	public List<Integer> countClientsPerGuild() {
		return this.dashboardRepository.countClientsPerGuild();
	}

	
}
