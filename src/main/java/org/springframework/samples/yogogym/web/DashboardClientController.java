package org.springframework.samples.yogogym.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.yogogym.service.DashboardClientService;
import org.springframework.stereotype.Controller;

@Controller
public class DashboardClientController {
	
	private final DashboardClientService dashboardClientService;
	
	@Autowired
	public DashboardClientController(final DashboardClientService dashboardClientService) {
		this.dashboardClientService = dashboardClientService;
	}

}
