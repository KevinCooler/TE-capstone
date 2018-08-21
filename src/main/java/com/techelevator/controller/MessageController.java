package com.techelevator.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.DAOs.ClientDAO;
import com.techelevator.model.DAOs.CoachDAO;
import com.techelevator.model.DAOs.MessageDAO;
import com.techelevator.model.DAOs.UserDAO;
import com.techelevator.model.Objects.Client;
import com.techelevator.model.Objects.Coach;
import com.techelevator.model.Objects.User;

@Controller
@SessionAttributes("currentUser")
public class MessageController {
	
	private CoachDAO coachDAO;
	private ClientDAO clientDAO;
	private MessageDAO messageDAO;
	private UserDAO userDAO;
	
	@Autowired
	public MessageController(CoachDAO coachDAO, ClientDAO clientDAO, MessageDAO messageDAO, UserDAO userDAO) {
		this.clientDAO = clientDAO;
		this.coachDAO = coachDAO;
		this.messageDAO = messageDAO;
		this.userDAO = userDAO;
	}

	@RequestMapping(path="/messages", method=RequestMethod.GET)
	public String displayMessages(ModelMap map, HttpSession session) {
		if(session.getAttribute("currentUser") != null) {
			User user = (User)session.getAttribute("currentUser");
			
			map.addAttribute("messages", messageDAO.getMessages(user.getId()));
		}
		
		return "messages";
	}
	
	@RequestMapping(path="/addMessage", method=RequestMethod.POST)
	public String addMessage(@RequestParam long receiverId,
			@RequestParam String receiverName,
			@RequestParam String messageText,
			HttpSession session) {
		User user = (User)session.getAttribute("currentUser");
		
		messageDAO.addMessage(user.getId(), getSenderName(user),
				receiverId, receiverName, messageText);

		return "redirect:/messages";
	}
	
	@RequestMapping(path="/newResponse", method=RequestMethod.GET)
	public String newResponse(@RequestParam String recipientName,
			@RequestParam long recipientId, ModelMap map) {
		
		map.addAttribute("recipientName", recipientName);
		map.addAttribute("recipientId", recipientId);
		
		return "newMessage";
	}
	
	@RequestMapping(path="/viewProfile", method=RequestMethod.GET)
	public String viewProfile(@RequestParam long userId,  RedirectAttributes redirect) {
		User user = userDAO.getUserByUserId(userId);
		if(user == null) return "redirect:/messages";
		if(user.getRole().equals("coach")) {
			redirect.addFlashAttribute("coachId", userId);
			return "redirect:/coach";
		} else if(user.getRole().equals("client")) {
			redirect.addFlashAttribute("clientId", userId);
			return "redirect:/client";
		}
		return "redirect:/messages";
	}
	
	private String getSenderName(User user) {
		if(user.getRole().equals("coach")) {
			Coach coach = coachDAO.getCoachById(user.getId());
			return coach.getFirstName() + " " + coach.getLastName();
		} else if(user.getRole().equals("client")) {
			Client client = clientDAO.getClientById(user.getId());
			return client.getFirstName() + " " + client.getLastName();
		} else if(user.getRole().equals("admin"))
			return "Administrator";
		
		return "redirect:/messages";
	}
}