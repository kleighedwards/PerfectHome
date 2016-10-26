package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.UserDAO;
import entities.HomeUser;
import entities.User;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;

	// Marco Polo To Test Connection Successful
	@RequestMapping(path = "/marco", method = RequestMethod.GET)
	public String ping() {
		return "polo";
	}

	// Returns All Users
	@RequestMapping(path = "user", method = RequestMethod.GET)
	public List<User> index() {
		return userDAO.index();
	}

	// Returns All HomeUsers
	@RequestMapping(path = "homeuser", method = RequestMethod.GET)
	public List<HomeUser> indexHomeUser() {
		return userDAO.indexHomeUser();
	}
}
