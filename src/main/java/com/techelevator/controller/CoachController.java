package com.techelevator.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Coach;
import com.techelevator.model.CoachDAO;

@Controller
public class CoachController {
	
	private CoachDAO coachDAO;
	
	
	@Autowired
	public CoachController(CoachDAO coachDAO) {
		this.coachDAO = coachDAO;
	}
	
	@RequestMapping(path="/coach", method=RequestMethod.GET)
	public String displayCoach(@RequestParam(required=false) Long coachId, 
			ModelMap map, Model model) {
		if(model.containsAttribute("coachId")) {
			long id = (Long)model.asMap().get("coachId");
			Coach coach = coachDAO.getCoachById(id);
			map.addAttribute("coach", coach);
		} else if(coachId == null) {
			return "home";
		} else {
			Coach coach = coachDAO.getCoachById(coachId);
			map.addAttribute("coach", coach);
		}
		
		return "coach";
	}
	
	@RequestMapping(path="/editCoach", method=RequestMethod.GET)
	public String displayEditCoachForm(@RequestParam long coachId, ModelMap map) {
		Coach coach = coachDAO.getCoachById(coachId);
		map.addAttribute("coach", coach);
		
		return "editCoach";
	}
	
	@ExceptionHandler(Exception.class)
	void handleException(Exception e, HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
	
	@RequestMapping(path="/editCoach", method=RequestMethod.POST)
	public String updateCoachInfo(@RequestParam long coachId, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String city,
			@RequestParam String state, @RequestParam String aboutMe,
			RedirectAttributes redirect) {
		coachDAO.updateName(firstName, lastName, coachId);
		coachDAO.updateLocation(city, state, coachId);
		coachDAO.updateAboutMe(aboutMe, coachId);
		
		redirect.addFlashAttribute("coachId", coachId);
		
		return "redirect:/coach";
	}
}