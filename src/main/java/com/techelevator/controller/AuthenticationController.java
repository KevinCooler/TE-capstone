package com.techelevator.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.DAOs.ClientDAO;
import com.techelevator.model.DAOs.CoachDAO;
import com.techelevator.model.DAOs.FeedbackDAO;
import com.techelevator.model.DAOs.UserDAO;
import com.techelevator.model.Objects.User;

@Controller
@SessionAttributes("currentUser")
public class AuthenticationController {

	private UserDAO userDAO;
	private CoachDAO coachDAO;
	private ClientDAO clientDAO;
	private FeedbackDAO feedbackDAO;

	@Autowired
	public AuthenticationController(UserDAO userDAO, CoachDAO coachDAO, ClientDAO clientDAO, FeedbackDAO feedbackDAO) {
		this.userDAO = userDAO;
		this.clientDAO = clientDAO;
		this.coachDAO = coachDAO;
		this.feedbackDAO = feedbackDAO;
	}
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String displayHomePage() {
		return "home";
	}

	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String displayLoginForm() {
		return "login";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(@RequestParam String userName, 
						@RequestParam String password, 
						@RequestParam(required=false) String destination,
						HttpSession session,
						ModelMap map) {
		if(userDAO.searchForUsernameAndPassword(userName, password)) {
			User user = userDAO.getUserByUserName(userName);
			session.setAttribute("currentUser", user);
			
			if(destination != null && !destination.isEmpty()) {
				return "redirect:" + destination;
			} else {
				if(user.getRole().equals("admin")) {
					return "redirect:/admin";
				}
				if(user.getRole().equals("coach")) {
					map.addAttribute("coachId",  coachDAO.getCoachById(user.getId()).getId());
					return "redirect:/coach";
				}
				else {
					map.addAttribute("clientId", clientDAO.getClientById(user.getId()).getId());
					return "redirect:/client";
				}
			}
		} else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path="/signUp", method=RequestMethod.GET)
	public String displaySignUpForm() {
		return "newUser";
	}
	
	@RequestMapping(path="/signUp", method=RequestMethod.POST)
	public String doSignUp(@RequestParam String userName, 
						   @RequestParam String password,
						   @RequestParam String firstName,
						   @RequestParam String lastName,
						   RedirectAttributes attr) {
		if(userDAO.getUserByUserName(userName) != null) {
			attr.addFlashAttribute("userNameError", "Oops! This email address is already in use. Please try again.");
			return "redirect:/signUp";
		}
		long clientId = userDAO.saveUser(userName, password, "client");
		clientDAO.addClient(firstName, lastName, clientId);
		feedbackDAO.createClientFeedback(clientId);
		return "redirect:/login";
	}

	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		model.remove("currentUser");
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(path="/changePassword", method=RequestMethod.GET)
	public String displayChangePasswordForm() {
		return "changePassword";
	}
	
	@RequestMapping(path="/changePassword", method=RequestMethod.POST)
	public String doChangePassword(@RequestParam String oldPassword,
						   		   @RequestParam String newPassword,
						   		   @RequestParam String confirmNewPassword,
						   		   HttpSession session,
						   		   RedirectAttributes redirect) {
		if(session.getAttribute("currentUser") == null) {
			return "redirect:/login";
		} else {
			User user = (User) session.getAttribute("currentUser");
			String userName = user.getUserName();
			if(userDAO.searchForUsernameAndPassword(userName, oldPassword) && newPassword.equals(confirmNewPassword)) {
				userDAO.updatePassword(userName, newPassword);
				return "redirect:/successChangePassword";
			}
			redirect.addFlashAttribute("incorrectPassword",  "Oops! The Old Password entered was not correct.");
			return "redirect:/changePassword";
		}
	}
	
	@RequestMapping(path="/successChangePassword", method=RequestMethod.GET)
	public String displaySuccessChangePassword() {
		return "successChangePassword";
	}
}