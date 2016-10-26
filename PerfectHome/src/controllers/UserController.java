package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.UserDAO;
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

	// Returns A Single User With The Provided ID
	@RequestMapping(path = "user/{id}", method = RequestMethod.GET)
	public User show(@PathVariable int id) {
		return userDAO.show(id);
	}

	// Adds A New User
	@RequestMapping(path = "user", method = RequestMethod.POST)
	public void create(@RequestBody String jsonUser, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		User newUser = null;

		try {
			newUser = mapper.readValue(jsonUser, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newUser == null) {
			response.setStatus(400);
		} else {
			response.setStatus(201);
			userDAO.create(newUser);
		}
	}

	// Delete A User
	@RequestMapping(path = "user/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {

		try {
			userDAO.destroy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
