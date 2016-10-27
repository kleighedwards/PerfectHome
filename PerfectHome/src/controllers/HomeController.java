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

import data.HomeDAO;
import entities.Home;

@RestController
public class HomeController {

	@Autowired
	private HomeDAO homeDAO;

	// Returns All Homes
	@RequestMapping(path = "home", method = RequestMethod.GET)
	public List<Home> index() {
		return homeDAO.index();
	}

	// Returns A Single Home With The Provided ID
	@RequestMapping(path = "home/{id}", method = RequestMethod.GET)
	public Home show(@PathVariable int id) {
		return homeDAO.show(id);
	}

	// Adds A New Home
	@RequestMapping(path = "home", method = RequestMethod.POST)
	public void create(@RequestBody String jsonHome, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Home newHome = null;

		try {
			newHome = mapper.readValue(jsonHome, Home.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newHome == null) {
			response.setStatus(400);
		} else {
			response.setStatus(201);
			homeDAO.create(newHome);
		}
	}

	// Delete A Home
	@RequestMapping(path = "home/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {

		try {
			homeDAO.destroy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
