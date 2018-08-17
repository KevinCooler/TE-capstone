package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.DAOs.AvailabilityDAO;
import com.techelevator.model.DAOs.ClientDAO;
import com.techelevator.model.DAOs.CoachDAO;
import com.techelevator.model.DAOs.FeedbackDAO;
import com.techelevator.model.DAOs.UserDAO;
import com.techelevator.model.Objects.Client;
import com.techelevator.model.Objects.Coach;
import com.techelevator.model.Objects.Feedback;

@Controller
public class ClientController {
	
	private CoachDAO coachDAO;
	private ClientDAO clientDAO;
	private FeedbackDAO feedbackDAO;
	
	@Autowired
	public ClientController(CoachDAO coachDAO, ClientDAO clientDAO, FeedbackDAO feedbackDAO, UserDAO userDAO, AvailabilityDAO availDAO) {
		this.clientDAO = clientDAO;
		this.coachDAO = coachDAO;
		this.feedbackDAO = feedbackDAO;
	}
	
	@RequestMapping(path="/browseCoaches", method=RequestMethod.GET)
	public String displayBrowseCoaches(ModelMap map) {
		map.addAttribute("coaches", coachDAO.getCoachList());
		return "browseCoaches";
	}
	
	@RequestMapping(path="/client", method=RequestMethod.GET)
	public String displayClientProfile(@RequestParam(required=false) Long clientId, ModelMap map, Model model) {
		Client client;
		List<Feedback> feedbacks;
		
		if(model.containsAttribute("clientId")) {
			long id = (long) model.asMap().get("clientId");
			client = clientDAO.getClientById(id);
			feedbacks = feedbackDAO.getFeedbackByClientId(id);
			map.addAttribute("feedbacks", feedbacks);
			map.addAttribute("client", client);
		} else if(clientId == null) {
			return "redirect:/";
		} else {
			client = clientDAO.getClientById(clientId);
			feedbacks = feedbackDAO.getFeedbackByClientId(clientId);
			map.addAttribute("feedbacks", feedbacks);
			map.addAttribute("client", client);
		} if(client != null) {
								//do we need to add feedback to modelmap here?
			return "client";
		}
		return "redirect:/";
	}

	@RequestMapping(path="/messageCoach", method=RequestMethod.GET)
	public String sendMessageToCoach(@RequestParam long coachId, ModelMap map) {
		Coach coach = coachDAO.getCoachById(coachId);
		
		map.addAttribute("recipientName", coach.getFirstName()
				+ " " + coach.getLastName());
		map.addAttribute("recipientId", coach.getId());
		
		return "newMessage";
	}
	
	@RequestMapping(path="/editClient", method=RequestMethod.GET)
	public String displayEditClientForm(@RequestParam(required=false) Long clientId, 
									   ModelMap map, 
									   Model model) {
		if(model.containsAttribute("clientId")) {
			long id = (Long)model.asMap().get("clientId");
			Client client = clientDAO.getClientById(id);
			map.addAttribute("client", client);
			List<Feedback> feedbackList = feedbackDAO.getFeedbackByClientId(clientId);
			map.addAttribute("feedbackList", feedbackList);
		} else if(clientId == null) {
			return "redirect:/";
		} else {
			Client client = clientDAO.getClientById(clientId);
			map.addAttribute("client", client);
			List<Feedback> feedbackList = feedbackDAO.getFeedbackByClientId(clientId);
			map.addAttribute("feedbackList", feedbackList);
		}
	
		return "editClient";
	}
	
	@RequestMapping(path="/editClient", method=RequestMethod.POST)
	public String updateClientInfo(@RequestParam long clientId, 
								  @RequestParam String firstName,
								  @RequestParam String lastName, @RequestParam String city,
								  @RequestParam String state, @RequestParam String aboutMe,
								  @RequestParam boolean isLookingForCoach,
								  RedirectAttributes redirect) {
		clientDAO.updateName(firstName, lastName, clientId);
		clientDAO.updateLocation(city, state, clientId);
		clientDAO.updateAboutMe(aboutMe, clientId);
		clientDAO.updateIsLookingForCoach(isLookingForCoach, clientId);
		
		redirect.addFlashAttribute("clientId", clientId);
		
		return "redirect:/client";
	}
	@RequestMapping(path="/submitModuleFeedback", method=RequestMethod.POST)
	public String addFeedbackForModule(@RequestParam long clientId,
									   @RequestParam int module,
									   @RequestParam String detail,
									   RedirectAttributes redirect) {
		feedbackDAO.addFeedback(clientId, module, detail);
		
		redirect.addFlashAttribute("clientId", clientId);
		
		return "redirect:/editClient";
		
	}
}