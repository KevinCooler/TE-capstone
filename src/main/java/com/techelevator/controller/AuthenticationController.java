package com.techelevator.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.DAOs.MessageDAO;
import com.techelevator.model.DAOs.UserDAO;
import com.techelevator.model.Objects.User;

@Controller
public class AuthenticationController {

	private UserDAO userDAO;
	private MessageDAO messageDAO;

	@Autowired
	public AuthenticationController(UserDAO userDAO, MessageDAO messageDAO) {
		this.userDAO = userDAO;
		this.messageDAO = messageDAO;
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
						HttpSession session) {
		if(userDAO.searchForUsernameAndPassword(userName, password)) {
			User user = userDAO.getUserByUserName(userName);
			session.setAttribute("currentUser", user);
			
			if(destination != null && ! destination.isEmpty()) {
				return "redirect:" + destination;
			} else {
				if(user.getRole().equals("admin")) {
					return "redirect:/admin";
				}
				if(user.getRole().equals("coach")) {
					//get coach object from database
					return "redirect:/coach";
				}
				else return "redirect:/users/"+userName;
			}
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		model.remove("currentUser");
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(path="/addMessage", method=RequestMethod.POST)
	public String addMessage(@RequestParam(required=false) Long clientId,
			@RequestParam(required=false) Long coachId,
			@RequestParam String messageText,
			HttpSession session) {
		//User user = (User)session.getAttribute("currentUser");
		User user = userDAO.getUserByUserName("John");
		
		if(clientId != null)
			messageDAO.addMessage(clientId, user.getId(), messageText);
		else if(coachId != null)
			messageDAO.addMessage(user.getId(), coachId, messageText);
		else
			return "redirect:/";
		
		return "redirect:/";
	}
}