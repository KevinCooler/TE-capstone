package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.Coach;
import com.techelevator.model.CoachDAO;
import com.techelevator.model.UserDAO;

@Controller
public class CoachController {
	
	private CoachDAO coachDAO;
	
	
	@Autowired
	public CoachController(CoachDAO coachDAO) {
		this.coachDAO = coachDAO;
	}
	
	@RequestMapping(path="/coach", method=RequestMethod.GET)
	public String displayCoach(@RequestParam long coachId, ModelMap map) {
		Coach coach = coachDAO.getCoachById(coachId);
		map.addAttribute("coach", coach);
		return "coach";
	}
	
	@RequestMapping(path="/editCoach", method=RequestMethod.GET)
	public String displayEditCoachForm() {
		return "editCoach";
	}
	

}
