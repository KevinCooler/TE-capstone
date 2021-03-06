package com.techelevator.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.DAOs.AvailabilityDAO;
import com.techelevator.model.DAOs.CoachDAO;
import com.techelevator.model.DAOs.MessageDAO;
import com.techelevator.model.DAOs.ReviewDAO;
import com.techelevator.model.DAOs.UserDAO;
import com.techelevator.model.Objects.Coach;
import com.techelevator.model.Objects.User;
import com.techelevator.security.PageAuthorizer;

@SessionAttributes("currentUser")
@Controller
public class AdminController {

	private CoachDAO coachDAO;
	private UserDAO userDAO;
	private MessageDAO messageDAO;
	private PageAuthorizer authorizer = new PageAuthorizer();

	@Autowired
	public AdminController(CoachDAO coachDAO, UserDAO userDAO, AvailabilityDAO availDAO, ReviewDAO reviewDAO, MessageDAO messageDAO) {
		this.coachDAO = coachDAO;
		this.userDAO = userDAO;
		this.messageDAO = messageDAO;
	}
	
	@RequestMapping(path="/admin", method=RequestMethod.GET)
	public String displayAdminPage(ModelMap map, HttpSession session) {
		if(authorizer.isNotAdmin((User) session.getAttribute("currentUser"))) return "redirect:/";
		List<Coach> coaches = coachDAO.getCoachList();
		map.addAttribute("coaches", coaches);
		List<User> users = new ArrayList<User>();
		for(Coach coach: coaches) {
			users.add(userDAO.getUserByUserId(coach.getId()));
		}
		map.addAttribute("users",  users);
		return "admin";
	}
	
	@RequestMapping(path="/addCoach", method=RequestMethod.POST)
	public String submitAddCoach(@RequestParam("firstName") String firstName, 
								 @RequestParam("lastName") String lastName, 
								 @RequestParam("password") String password, 
								 @RequestParam("userName") String userName,
								 @RequestParam("confirmPassword") String confirmPassword,
								 RedirectAttributes redirect) {
		if(userDAO.getUserByUserName(userName) != null) {
			redirect.addFlashAttribute("duplicateUsername", "Oops! This email address is already in use. Please try again.");
		}
		else {
			Long coachId = userDAO.saveUser(userName, password, "coach");
			coachDAO.addCoach(firstName, lastName, coachId);
		}
		return "redirect:/admin";
	}
	
	@RequestMapping(path="/deleteCoach", method=RequestMethod.GET)
	public String doDeleteCoach(@RequestParam long coachId, HttpSession session, RedirectAttributes redirect) {
		if(authorizer.isNotAdmin((User) session.getAttribute("currentUser"))) return "redirect:/";
		
		messageDAO.removeMessagesByUserId(coachId);
		coachDAO.removeCoach(coachId);
		userDAO.deleteUserByUserId(coachId);
		return "redirect:/admin";
	}
}