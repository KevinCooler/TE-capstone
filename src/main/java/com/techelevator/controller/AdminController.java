package com.techelevator.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.AvailabilityDAO;
import com.techelevator.model.CoachDAO;
import com.techelevator.model.ReviewDAO;
import com.techelevator.model.UserDAO;

@Controller
public class AdminController {

	private CoachDAO coachDAO;
	private UserDAO userDAO;

	@Autowired
	public AdminController(CoachDAO coachDAO, UserDAO userDAO, AvailabilityDAO availDAO, ReviewDAO reviewDAO) {
		this.coachDAO = coachDAO;
		this.userDAO = userDAO;
	}
	
	@RequestMapping(path="/admin", method=RequestMethod.GET)
	public String displayAdminPage(ModelMap map) {
		map.addAttribute("coaches", coachDAO.getCoachList());
		return "admin";
	}
	
	@RequestMapping(path="/addCoach", method=RequestMethod.POST)
	public String submitAddCoach(@RequestParam("firstName") String firstName, 
								 @RequestParam("lastName") String lastName, 
								 @RequestParam("password") String password, 
								 @RequestParam("confirmPassword") String confirmPassword) {
		String userName = firstName.substring(0, 1).toLowerCase() + lastName.toLowerCase();
		Long coachId = userDAO.saveUser(userName, password, "coach");
		coachDAO.addCoach(firstName, lastName, coachId);
		return "redirect:/admin";
	}
	
	@ExceptionHandler(Exception.class)
	void handleException(Exception e, HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	}
	
	@RequestMapping(path="/deleteCoach", method=RequestMethod.GET)
	public String doDeleteCoach(@RequestParam long coachId) {
		coachDAO.removeCoach(coachId);
		userDAO.deleteUserByUserId(coachId);
		return "redirect:/admin";
	}
}