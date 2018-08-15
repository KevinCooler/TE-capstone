package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.DAOs.AvailabilityDAO;
import com.techelevator.model.DAOs.ClientDAO;
import com.techelevator.model.DAOs.CoachDAO;
import com.techelevator.model.DAOs.UserDAO;
import com.techelevator.model.Objects.Client;
import com.techelevator.model.Objects.Coach;

@Controller
public class ClientController {
	
	private CoachDAO coachDAO;
	private ClientDAO clientDAO;
//	private UserDAO userDAO;
//	private AvailabilityDAO availDAO;
	
	@Autowired
	public ClientController(CoachDAO coachDAO, ClientDAO clientDAO, UserDAO userDAO, AvailabilityDAO availDAO) {
		this.clientDAO = clientDAO;
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
	public String displayClientProfile(@RequestParam(required=false) Long clientId, ModelMap map, Model model) {
		Client client;
		
		if(model.containsAttribute("clientId")) {
			long id = (long) model.asMap().get("clientId");
			client = clientDAO.getClientById(id);
			map.addAttribute("client", client);
		} else if(clientId == null) {
			return "redirect:/";
		} else {
			client = clientDAO.getClientById(clientId);
			map.addAttribute("client", client);
		} if(client != null) {
			return "client";
		}
		return "redirect:/";
	}

	@RequestMapping(path="/newMessage", method=RequestMethod.GET)
	public String displayMessageForm(@RequestParam long clientId,
			@RequestParam long coachId, ModelMap map) {
		Coach coach = coachDAO.getCoachById(coachId);
		
		map.addAttribute("coach", coach);
		map.addAttribute("clientId", clientId);
		
		return "message";
	}
}