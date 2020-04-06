package org.springframework.samples.yogogym.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
