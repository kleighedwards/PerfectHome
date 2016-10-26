package controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.HomeUserDAO;
import entities.Home;
import entities.HomeUser;
import entities.Note;
import entities.Todo;
import entities.User;

@RestController
public class HomeUserController {

	@Autowired
	private HomeUserDAO huDAO;

	// Returns All HomeUsers
	@RequestMapping(path = "homeuser", method = RequestMethod.GET)
	public List<HomeUser> indexHomeUser() {
		return huDAO.index();
	}

	// Returns A Single HomeUser With The Provided ID
	@RequestMapping(path = "homeuser/{id}", method = RequestMethod.GET)
	public HomeUser show(@PathVariable int id) {
		return huDAO.show(id);
	}

	// Returns A User
	@RequestMapping(path = "homeuser/{id}/user", method = RequestMethod.GET)
	public User showUser(@PathVariable int id) {
		return huDAO.show(id).getUser();
	}

	// Returns A Home
	@RequestMapping(path = "homeuser/{id}/home", method = RequestMethod.GET)
	public Home showHome(@PathVariable int id) {
		return huDAO.show(id).getHome();
	}

	// Returns ToDo's
	@RequestMapping(path = "homeuser/{id}/todos", method = RequestMethod.GET)
	public Collection<Todo> showTodos(@PathVariable int id) {
		return huDAO.show(id).getTodos();
	}

	// Returns Notes
	@RequestMapping(path = "homeuser/{id}/notes", method = RequestMethod.GET)
	public Collection<Note> showNotes(@PathVariable int id) {
		return huDAO.show(id).getNotes();
	}

}
