package com.techelevator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.CoachDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
public class AdminController {

	private CoachDAO coachDAO;

	@Autowired
	public AdminController(CoachDAO coachDAO) {
		this.coachDAO = coachDAO;
	}
	
	@RequestMapping(path="/admin", method=RequestMethod.GET)
	public String displayAdminPage(ModelMap map) {
		map.addAttribute("coaches", coachDAO.getCoachList());
		return "admin";
	}
	
	@RequestMapping(path="/addCoach", method=RequestMethod.POST)
	public String submitAddCoach(@RequestParam long coachId) {
		return "redirect:/admin";
	}
	
	@RequestMapping(path="/deleteCoach", method=RequestMethod.POST)
	public String doDeleteCoach(@RequestParam long coachId) {
		coachDAO.removeCoach(coachId);
		return "redirect:/admin";
	}
	
	
	
}
