package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import data.NoteDAO;

@RestController
public class NoteController {
	
	@Autowired
	private NoteDAO todoDAO;

}
