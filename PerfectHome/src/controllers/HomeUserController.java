package controllers;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.HomeUserDAO;
import entities.Home;
import entities.HomeUser;
import entities.Image;
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

	// Returns A HomeUser Using User ID and Home ID
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}", method = RequestMethod.GET)
	public HomeUser showByIds(@PathVariable int u_Id, @PathVariable int h_Id) {
		return huDAO.showByIds(u_Id, h_Id);
	}

	// Returns Rating
	@RequestMapping(path = "homeuser/{id}/rating", method = RequestMethod.GET)
	public int showRating(@PathVariable int id) {
		return huDAO.show(id).getRating();
	}

	// Returns Returns Rating Using Home and User IDs
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}/rating", method = RequestMethod.GET)
	public int showRatingByIds(@PathVariable int u_Id, @PathVariable int h_Id) {
		return huDAO.showByIds(u_Id, h_Id).getRating();
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

	// Returns ToDo's Using User ID and Home ID
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}/todos", method = RequestMethod.GET)
	public Collection<Todo> showTodosByIds(@PathVariable int u_Id, @PathVariable int h_Id) {
		return huDAO.showByIds(u_Id, h_Id).getTodos();
	}

	// Returns Notes
	@RequestMapping(path = "homeuser/{id}/notes", method = RequestMethod.GET)
	public Collection<Note> showNotes(@PathVariable int id) {
		return huDAO.show(id).getNotes();
	}

	// Returns Notes Using User ID and Home ID
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}/notes", method = RequestMethod.GET)
	public Collection<Note> showNotesByIds(@PathVariable int u_Id, @PathVariable int h_Id) {
		return huDAO.showByIds(u_Id, h_Id).getNotes();
	}

	// Returns Images
	@RequestMapping(path = "homeuser/{id}/images", method = RequestMethod.GET)
	public Collection<Image> showImages(@PathVariable int id) {
		return huDAO.show(id).getImages();
	}

	// Returns Images Using User ID and Home ID
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}/images", method = RequestMethod.GET)
	public Collection<Image> showImagesByIds(@PathVariable int u_Id, @PathVariable int h_Id) {
		return huDAO.showByIds(u_Id, h_Id).getImages();
	}

	// Adds A New HomeUser Relationship
	@RequestMapping(path = "homeuser", method = RequestMethod.POST)
	public void create(@RequestBody String jsonHu, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		HomeUser newHu = null;

		try {
			newHu = mapper.readValue(jsonHu, HomeUser.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newHu == null) {
			response.setStatus(400);
		} else {
			response.setStatus(201);
			huDAO.create(newHu);
		}
	}

	// Delete A HomeUser Relationship
	@RequestMapping(path = "homeuser/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {

		try {
			huDAO.destroy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Delete A HomeUser Relationship Using User ID and Home ID
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}", method = RequestMethod.DELETE)
	public void deleteByIds(@PathVariable int u_Id, @PathVariable int h_Id) {

		try {
			huDAO.destroyByIds(u_Id, h_Id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Create A ToDo
	@RequestMapping(path = "homeuser/{id}/todos", method = RequestMethod.POST)
	public void createTodo(@PathVariable int id, @RequestBody String jsonTodo, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Todo newTodo = null;

		try {
			newTodo = mapper.readValue(jsonTodo, Todo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newTodo == null) {
			response.setStatus(400);
		} else {
			response.setStatus(201);
			huDAO.createTodo(id, newTodo);
		}
	}

	// Create A ToDo Using User ID and Home ID
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}/todos", method = RequestMethod.POST)
	public void createTodoByIds(@PathVariable int u_Id, @PathVariable int h_Id, @RequestBody String jsonTodo,
			HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Todo newTodo = null;

		try {
			newTodo = mapper.readValue(jsonTodo, Todo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newTodo == null) {
			response.setStatus(400);
		} else {
			response.setStatus(201);
			huDAO.createTodoByIds(u_Id, h_Id, newTodo);
		}
	}

	// Create A Note
	@RequestMapping(path = "homeuser/{id}/notes", method = RequestMethod.POST)
	public void createNote(@PathVariable int id, @RequestBody String jsonNote, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Note newNote = null;

		try {
			newNote = mapper.readValue(jsonNote, Note.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newNote == null) {
			response.setStatus(400);
		} else {
			response.setStatus(201);
			huDAO.createNote(id, newNote);
		}
	}

	// Create A Note Using User ID and Home ID
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}/notes", method = RequestMethod.POST)
	public void createNoteByIds(@PathVariable int u_Id, @PathVariable int h_Id, @RequestBody String jsonNote,
			HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Note newNote = null;

		try {
			newNote = mapper.readValue(jsonNote, Note.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newNote == null) {
			response.setStatus(400);
		} else {
			response.setStatus(201);
			huDAO.createNoteByIds(u_Id, h_Id, newNote);
		}
	}

	// Create An Image
	@RequestMapping(path = "homeuser/{id}/images", method = RequestMethod.POST)
	public void createImage(@PathVariable int id, @RequestBody String jsonImage, HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Image newImage = null;

		try {
			newImage = mapper.readValue(jsonImage, Image.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newImage == null) {
			response.setStatus(400);
		} else {
			response.setStatus(201);
			huDAO.createImage(id, newImage);
		}
	}

	// Create An Image Using User ID and Home ID
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}/images", method = RequestMethod.POST)
	public void createImageByIds(@PathVariable int u_Id, @PathVariable int h_Id, @RequestBody String jsonImage,
			HttpServletResponse response) {
		ObjectMapper mapper = new ObjectMapper();
		Image newImage = null;

		try {
			newImage = mapper.readValue(jsonImage, Image.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (newImage == null) {
			response.setStatus(400);
		} else {
			response.setStatus(201);
			huDAO.createImageByIds(u_Id, h_Id, newImage);
		}
	}

	// Delete ToDo
	@RequestMapping(path = "homeuser/{id}/todos/{t_id}", method = RequestMethod.DELETE)
	public void deleteTodo(@PathVariable int id, @PathVariable int t_id) {

		try {
			huDAO.destroyTodo(id, t_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Delete ToDo Using User ID and Home ID
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}/todos/{t_id}", method = RequestMethod.DELETE)
	public void deleteTodoByIds(@PathVariable int u_Id, @PathVariable int h_Id, @PathVariable int t_id) {

		try {
			huDAO.destroyTodoByIds(u_Id, h_Id, t_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Delete Note
	@RequestMapping(path = "homeuser/{id}/notes/{n_id}", method = RequestMethod.DELETE)
	public void deleteNote(@PathVariable int id, @PathVariable int n_id) {

		try {
			huDAO.destroyNote(id, n_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Delete Note Using User ID and Home ID
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}/notes/{n_id}", method = RequestMethod.DELETE)
	public void deleteNoteByIds(@PathVariable int u_Id, @PathVariable int h_Id, @PathVariable int n_id) {

		try {
			huDAO.destroyNoteByIds(u_Id, h_Id, n_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Delete Image
	@RequestMapping(path = "homeuser/{id}/images/{i_id}", method = RequestMethod.DELETE)
	public void deleteImage(@PathVariable int id, @PathVariable int i_id) {

		try {
			huDAO.destroyImage(id, i_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Delete Image Using User ID and Home ID
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}/images/{i_id}", method = RequestMethod.DELETE)
	public void deleteImageByIds(@PathVariable int u_Id, @PathVariable int h_Id, @PathVariable int i_id) {

		try {
			huDAO.destroyImageByIds(u_Id, h_Id, i_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Update A ToDo
	@RequestMapping(path = "homeuser/todos/{t_id}", method = RequestMethod.PUT)
	public void updateToDo(@PathVariable int t_id, @RequestBody String jsonTodo) {
		ObjectMapper mapper = new ObjectMapper();
		Todo editTodo = null;

		try {
			editTodo = mapper.readValue(jsonTodo, Todo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		huDAO.updateTodo(t_id, editTodo);
	}

	// Update A Note
	@RequestMapping(path = "homeuser/notes/{n_id}", method = RequestMethod.PUT)
	public void updateNote(@PathVariable int n_id, @RequestBody String jsonNote) {
		ObjectMapper mapper = new ObjectMapper();
		Note editNote = null;

		try {
			editNote = mapper.readValue(jsonNote, Note.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		huDAO.updateNote(n_id, editNote);
	}

	// Update Rating
	@RequestMapping(path = "homeuser/{id}/rating/{rating}", method = RequestMethod.PUT)
	public void updateRating(@PathVariable int id, @PathVariable int rating) {

		try {
			huDAO.updateRating(id, rating);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Update Rating Using IDs
	@RequestMapping(path = "homeuser/user/{u_Id}/home/{h_Id}/rating/{rating}", method = RequestMethod.PUT)
	public void updateRatingByIds(@PathVariable int u_Id, @PathVariable int h_Id, @PathVariable int rating) {

		try {
			huDAO.updateRatingByIds(u_Id, h_Id, rating);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
