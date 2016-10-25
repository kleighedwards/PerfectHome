package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import data.HomeDAO;

@RestController
public class HomeController {

	@Autowired
	private HomeDAO homeDAO;
}
