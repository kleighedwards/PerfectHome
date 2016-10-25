package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import data.ToDoDAO;

@RestController
public class ToDoController {
	
	@Autowired
	private ToDoDAO todoDAO;

}
