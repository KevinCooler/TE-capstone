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

import com.techelevator.model.DAOs.AvailabilityDAO;
import com.techelevator.model.DAOs.ClientDAO;
import com.techelevator.model.DAOs.CoachDAO;
import com.techelevator.model.DAOs.ReviewDAO;
import com.techelevator.model.Objects.Client;
import com.techelevator.model.Objects.Coach;
import com.techelevator.model.Objects.User;
import com.techelevator.security.PageAuthorizer;

@Controller
@SessionAttributes("currentUser")
public class CoachController {
	
	private CoachDAO coachDAO;
	private AvailabilityDAO availDAO;
	private ReviewDAO reviewDao;
	private ClientDAO clientDAO;
	private PageAuthorizer authorizer = new PageAuthorizer();
	
	@Autowired
	public CoachController(CoachDAO coachDAO, AvailabilityDAO availDAO, ReviewDAO reviewDao, ClientDAO clientDAO) {
		this.coachDAO = coachDAO;
		this.availDAO = availDAO;
		this.reviewDao = reviewDao;
		this.clientDAO = clientDAO;
	}
	
	@RequestMapping(path="/coach", method=RequestMethod.GET)
	public String displayCoach(@RequestParam(required=false) Long coachId, 
			ModelMap map, Model model) {
		Coach coach;
		
		if(model.containsAttribute("coachId")) {
			long id = (Long)model.asMap().get("coachId");
			coach = coachDAO.getCoachById(id);
			map.addAttribute("coach", coach);
		} else if(coachId == null) {
			return "redirect:/";
		} else {
			coach = coachDAO.getCoachById(coachId);
			map.addAttribute("coach", coach);
		}
		
		if(coach != null)
			return "coach";
		return "redirect:/";
	}
	
	@RequestMapping(path="/editCoach", method=RequestMethod.GET)
	public String displayEditCoachForm(@RequestParam(required=false) Long coachId, 
									   ModelMap map, 
									   Model model,
									   HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		if(model.containsAttribute("coachId")) {
			long id = (Long)model.asMap().get("coachId");
			if(authorizer.isNotAdmin(user) && authorizer.isNotThisUser(user,  id)) return "redirect:/";
			Coach coach = coachDAO.getCoachById(id);
			map.addAttribute("coach", coach);
		} else if(coachId == null) {
			return "redirect:/";
		} else {
			if(authorizer.isNotAdmin(user) && authorizer.isNotThisUser(user,  coachId)) return "redirect:/";
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
	
	@RequestMapping(path="/addReview", method=RequestMethod.POST)
	public String addCoachReview(@RequestParam long clientId,
			@RequestParam long coachId, @RequestParam int rating,
			@RequestParam String reviewText, RedirectAttributes redirect) {
		reviewDao.addReview(coachId, clientId, rating, reviewText);
		
		redirect.addFlashAttribute("coachId", coachId);
		
		return "redirect:/coach";
	}
	
	@RequestMapping(path="/browseClients", method=RequestMethod.GET)
	public String displayBrowseClients(ModelMap map, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		if(authorizer.isNotAdmin(user) && authorizer.isNotCoach(user)) return "redirect:/";
		map.addAttribute("clients", clientDAO.getClientList());
		return "browseClients";
	}
	
	@RequestMapping(path="/messageClient", method=RequestMethod.GET)
	public String sendMessageToClient(@RequestParam long clientId, ModelMap map) {
		Client client = clientDAO.getClientById(clientId);
		
		map.addAttribute("recipientName", client.getFirstName()
				+ " " + client.getLastName());
		map.addAttribute("recipientId", client.getId());
		
		return "newMessage";
	}
}