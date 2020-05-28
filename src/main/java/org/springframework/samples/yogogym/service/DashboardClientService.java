package org.springframework.samples.yogogym.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.repository.DashboardClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardClientService {

	@Autowired
	private DashboardClientRepository dashboardClientRepository;

	private final Calendar now = Calendar.getInstance();

	@Transactional(readOnly=true)
	public List<Integer> countBodyPart(Integer days, String username) {
		return this.dashboardClientRepository.countBodyPart(dateByDays(days), username);
	}

	@Transactional(readOnly=true)
	public List<String> nameBodyPart(Integer days, String username) {
		return this.dashboardClientRepository.nameBodyPart(dateByDays(days), username);
	}

	@Transactional(readOnly=true)
	public List<Integer> countRepetitionType(Integer days, String username) {
		return this.dashboardClientRepository.countRepetitionType(dateByDays(days), username);
	}

	@Transactional(readOnly=true)
	public List<String> nameRepetitionType(Integer days, String username) {
		return this.dashboardClientRepository.nameRepetitionType(dateByDays(days), username);
	}

	@Transactional(readOnly=true)
	public Integer sumKcal(Integer days, String username) {
		return this.dashboardClientRepository.sumKcal(dateByDays(days), username);
	}

	private Date dateByDays(Integer days) {
		Calendar now2 = (Calendar) now.clone();
		Date d2;
		if (days != null) {
			now2.add(Calendar.DAY_OF_MONTH, -days);
			d2 = now2.getTime();
		} else {
			now2.add(Calendar.DAY_OF_MONTH, -999999);
			d2 = now2.getTime();
		}
		return d2;
	}
}
