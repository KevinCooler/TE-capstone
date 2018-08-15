package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.model.AvailabilityDAO;
import com.techelevator.model.CoachDAO;
import com.techelevator.model.UserDAO;

@Controller
public class ClientController {
	
	private CoachDAO coachDAO;
//	private UserDAO userDAO;
//	private AvailabilityDAO availDAO;
	
	@Autowired
	public ClientController(CoachDAO coachDAO, UserDAO userDAO, AvailabilityDAO availDAO) {
		this.coachDAO = coachDAO;
//		this.userDAO = userDAO;
//		this.availDAO = availDAO;
	}
	
	@RequestMapping(path="/browseCoaches", method=RequestMethod.GET)
	public String displayBrowseCoaches(ModelMap map) {
		map.addAttribute("coaches", coachDAO.getCoachList());
		return "browseCoaches";
	}
	
	@RequestMapping(path="/client", method=RequestMethod.GET)
	public String displayClientProfile() {
		return "client";
	}

	
	
	
	
}

