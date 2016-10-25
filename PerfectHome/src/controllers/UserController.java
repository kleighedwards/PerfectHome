package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.UserDAO;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;

	// Marco Polo To Test Connection Successful
	@RequestMapping(path = "/marco", method = RequestMethod.GET)
	public String ping() {
		return "polo";
	}
}
