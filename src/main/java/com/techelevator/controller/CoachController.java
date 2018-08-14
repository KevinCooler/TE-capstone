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

import com.techelevator.model.AvailabilityDAO;
import com.techelevator.model.Coach;
import com.techelevator.model.CoachDAO;

@Controller
public class CoachController {
	
	private CoachDAO coachDAO;
	private AvailabilityDAO availDAO;
	
	
	@Autowired
	public CoachController(CoachDAO coachDAO, AvailabilityDAO availDAO) {
		this.coachDAO = coachDAO;
		this.availDAO = availDAO;
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
	public String displayEditCoachForm(@RequestParam(required=false) Long coachId, 
									   ModelMap map, 
									   Model model) {
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
	
		return "editCoach";
	}
	
	@RequestMapping(path="/editCoach", method=RequestMethod.POST)
	public String updateCoachInfo(@RequestParam long coachId, 
								  @RequestParam String firstName,
								  @RequestParam String lastName, @RequestParam String city,
								  @RequestParam String state, @RequestParam String aboutMe,
								  RedirectAttributes redirect) {
		coachDAO.updateName(firstName, lastName, coachId);
		coachDAO.updateLocation(city, state, coachId);
		coachDAO.updateAboutMe(aboutMe, coachId);
		
		redirect.addFlashAttribute("coachId", coachId);
		
		return "redirect:/coach";
	}
	
	@RequestMapping(path="/deleteAvailability", method=RequestMethod.GET)
	public String doDeleteAvailability(@RequestParam Long availId, 
									   @RequestParam long coachId, 
									   RedirectAttributes attr) {
		attr.addFlashAttribute("coachId", coachId);
		availDAO.removeAvailability(availId);
		return "redirect:/editCoach";
	}
	
	@RequestMapping(path="/addAvailability", method=RequestMethod.POST)
	public String doAddAvailability(@RequestParam int day,
									@RequestParam int startTime,
									@RequestParam int endTime,
									@RequestParam long coachId,
									RedirectAttributes attr) {
		attr.addFlashAttribute("coachId", coachId);
		availDAO.addAvailability(coachId, day, startTime, endTime);
		return "redirect:/editCoach";
	}
	
	
	
	
	

	
	
	
	
	
}