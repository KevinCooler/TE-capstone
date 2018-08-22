package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.DAOs.AvailabilityDAO;
import com.techelevator.model.DAOs.ClientDAO;
import com.techelevator.model.DAOs.CoachDAO;
import com.techelevator.model.DAOs.ReviewDAO;
import com.techelevator.model.Objects.Client;
import com.techelevator.model.Objects.Coach;
import com.techelevator.model.Objects.Review;
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
	ServletContext servletContext;
	
	@Autowired
	public CoachController(CoachDAO coachDAO, AvailabilityDAO availDAO, ReviewDAO reviewDao, ClientDAO clientDAO) {
		this.coachDAO = coachDAO;
		this.availDAO = availDAO;
		this.reviewDao = reviewDao;
		this.clientDAO = clientDAO;
	}
	
	@RequestMapping(path="/coach", method=RequestMethod.GET)
	public String displayCoach(@RequestParam(required=false) Long coachId, 
			ModelMap map, Model model, HttpSession session) {
		Coach coach;
		List<Client> clients;
		User user = (User) session.getAttribute("currentUser");
		
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

		if(user != null) {
			if(user.getRole().equals("client")) {
				Client client = clientDAO.getClientById(user.getId());
				map.addAttribute("client", client);
				map.addAttribute("prevReview", hasClientLeftReview(client.getId(), coach));
			} else {
				if(model.containsAttribute("coachId")) {
					long id = (Long)model.asMap().get("coachId");
					clients = clientDAO.getClientsByCoach(id);
				}
				else {
					clients = clientDAO.getClientsByCoach(coachId);
				}
				map.addAttribute("clients", clients);
			}
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
			if((authorizer.isNotAdmin(user) && authorizer.isNotThisUser(user,  id)) || 
					(authorizer.isNotAdmin(user) && authorizer.isNotCoach(user))) return "redirect:/";
			Coach coach = coachDAO.getCoachById(id);
			map.addAttribute("coach", coach);
		} else if(coachId == null) {
			return "redirect:/";
		} else {
			if((authorizer.isNotAdmin(user) && authorizer.isNotThisUser(user,  coachId)) || 
					(authorizer.isNotAdmin(user) && authorizer.isNotCoach(user))) return "redirect:/";
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
	
	//Profile Pic
	
	@RequestMapping(path="/uploadProfilePic", method=RequestMethod.POST)
	public String uploadPicture(@RequestParam("file") MultipartFile file, 
			HttpServletRequest request, RedirectAttributes redirect) {
		long coachId = Long.parseLong(request.getParameter("coachId"));
		
		File imagePath = getImageFilePath();
		String fileName = imagePath + File.separator + "coach" + coachId;
		
		System.out.println(fileName);
		createImage(file, fileName);
		
		redirect.addFlashAttribute("coachId", coachId);
		
		return "redirect:/coach";
	}
	
	private File getImageFilePath() {
		String serverPath = servletContext.getRealPath("/") + "img/profiles";
		File filePath = new File(serverPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		return filePath;
	}
	
	private void createImage(MultipartFile file, String name) {
		File image = new File(name);
		
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image))) {
			//System.out.println(file.getBytes().toString());
			stream.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Profile Pic
	
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
	public String addCoachReview(@RequestParam long coachId,
			@RequestParam int rating, @RequestParam String reviewText,
			RedirectAttributes redirect, HttpSession session) {
		User user = (User)session.getAttribute("currentUser");
		if(authorizer.isNotCoach(user))
			reviewDao.addReview(coachId, user.getId(), rating, reviewText);
		
		redirect.addFlashAttribute("coachId", coachId);
		
		return "redirect:/coach";
	}
	
	@RequestMapping(path="/editReview", method=RequestMethod.POST)
	public String editReview(@RequestParam long reviewId, @RequestParam long coachId,
			@RequestParam String reviewText, HttpSession session, @RequestParam int rating,
			RedirectAttributes redirect) {
		User user = (User)session.getAttribute("currentUser");
		
		if(user != null)
			reviewDao.editReview(reviewId, user.getId(), rating, reviewText);
		
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
	
	@RequestMapping(path="/coachClient", method=RequestMethod.POST)
	public String coachClient(@RequestParam long clientId,
			HttpSession session, RedirectAttributes redirect) {
		User user = (User)session.getAttribute("currentUser");
		
		if(user.getRole().equals("coach")) {
			clientDAO.updateIsLookingForCoach(false, clientId);
			clientDAO.assignCoach(clientId, user.getId());
		}
		
		redirect.addFlashAttribute("clientId", clientId);
		
		return "redirect:/client";
	}
	
	@RequestMapping(path="/noLongerCoaching", method=RequestMethod.POST)
	public String noLongerCoachingClient(@RequestParam long clientId,
			HttpSession session, RedirectAttributes redirect) {
		User user = (User)session.getAttribute("currentUser");
		
		if(user.getRole().equals("coach")) {
			clientDAO.assignCoach(clientId, null);
			clientDAO.updateIsLookingForCoach(true, clientId);
		}
		
		redirect.addFlashAttribute("clientId", clientId);
		
		return "redirect:/client";
	}
	
	@RequestMapping(path="/completedCourse", method=RequestMethod.POST)
	public String clientCompletedCourse(@RequestParam long clientId,
			HttpSession session, RedirectAttributes redirect) {
		User user = (User)session.getAttribute("currentUser");
		
		if(user.getRole().equals("coach"))
			clientDAO.updateCompleted(true, clientId);
		
		return "redirect:/client";
	}
	
	private boolean hasClientLeftReview(long clientId, Coach coach) {
		for(Review review : coach.getReviews()) {
			if(review.getClientId() == clientId)
				return true;
		}
		
		return false;
	}
}