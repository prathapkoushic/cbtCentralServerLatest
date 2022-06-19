package com.ttipl.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AttendenceReportController 
{

	private static final Logger LOGGER = LoggerFactory.getLogger(AttendenceReportController.class);
	@Autowired
	private HttpSession httpSession;
	
	@Value("classpath:static/sys")
	Resource resourceSysFile;

	@Value("classpath:static/Reports")
	Resource resourceReportsFile;

	@RequestMapping("/generateStickers")
	public String stickersPage(Model model)
	{
		if (httpSession.getAttribute("Location") == null) 
		{
			model.addAttribute("msg", "Session Expired Relogin again.");
			return "index";
		}
		AttendenceReportController.LOGGER.info("in stickersPage");
		return "stickers";

	}

	@RequestMapping("/generateAttendenceSheet")
	public String generateAttendenceSheet(Model model) {
		if (httpSession.getAttribute("Location") == null) {
			model.addAttribute("msg", "Session Expired Relogin again.");
			return "index";
		}
		AttendenceReportController.LOGGER.info("in generateAttendenceSheet");
		return "AttendenceSheet";
	}
	
	

}
