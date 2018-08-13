package com.techelevator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
public class AdminController {

	private UserDAO userDAO;

	@Autowired
	public AdminController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@RequestMapping(path="/admin", method=RequestMethod.GET)
	public String displayAdminPage() {
		return "admin";
	}
	
	@RequestMapping(path="/AddCoach", method=RequestMethod.POST)
	public String submitAddCoach() {
		return "redirect:/admin";
	}
	
	
	
}
