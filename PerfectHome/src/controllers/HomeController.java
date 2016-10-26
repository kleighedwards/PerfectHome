package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
