package org.springframework.samples.yogogym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.repository.DashboardClientRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardClientService {

	@Autowired
	private DashboardClientRepository dashboardClientRepository;

	public Integer[] countBodyPart(int month, int year, String username) {
		return this.dashboardClientRepository.countBodyPart(month, year, username);
	}

	public String[] nameBodyPart(int month, int year, String username) {
		return this.dashboardClientRepository.nameBodyPart(month, year, username);
	}

	public Integer[] countRepetitionType(int month, int year, String username) {
		return this.dashboardClientRepository.countRepetitionType(month, year, username);
	}

	public String[] nameRepetitionType(int month, int year, String username) {
		return this.dashboardClientRepository.nameRepetitionType(month, year, username);
	}

	public Integer sumKcal(int month, int year, String username) {
		return this.dashboardClientRepository.sumKcal(month, year, username);
	}

}
