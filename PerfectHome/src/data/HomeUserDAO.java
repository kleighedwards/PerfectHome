package data;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Home;
import entities.HomeUser;
import entities.Image;
import entities.Note;
import entities.Todo;
import entities.User;

@Transactional
public class HomeUserDAO {

	@PersistenceContext
	private EntityManager em;

	// Get All HomeUsers
	public List<HomeUser> index() {
		String query = "Select hu from HomeUser hu";
		List<HomeUser> homeUsers = em.createQuery(query, HomeUser.class).getResultList();

		return homeUsers;
	}

	// Get HomeUser By ID
	public HomeUser show(int id) {
		return em.find(HomeUser.class, id);
	}

	// Add New HomeUser Relationship
	public HomeUser create(HomeUser hu) {
		System.out.println(hu);
		User user = em.find(User.class, hu.getUser().getId());
		Home home = em.find(Home.class, hu.getHome().getId());

		hu.setUser(user);
		hu.setHome(home);

		em.persist(hu);
		em.flush();

		return hu;
	}

	// Delete HomeUser
	public void destroy(int id) {
		HomeUser deleteHu = em.find(HomeUser.class, id);
		User u = em.find(User.class, deleteHu.getUser().getId());
		
		Set<HomeUser> hus = u.getHomeUsers();
		
		hus.remove(deleteHu);
		u.setHomeUsers(hus);
		
		em.persist(u);
	}

	// Create A New ToDo
	public void createTodo(int id, Todo newTodo) {
		HomeUser hu = em.find(HomeUser.class, id);

		newTodo.setHomeUser(hu);

		em.persist(newTodo);
		em.flush();
	}

	// Create A New ToDo
	public void createNote(int id, Note newNote) {
		HomeUser hu = em.find(HomeUser.class, id);

		newNote.setHomeUser(hu);

		em.persist(newNote);
		em.flush();
	}

	// Create A New Image
	public void createImage(int id, Image newImage) {
		HomeUser hu = em.find(HomeUser.class, id);

		newImage.setHomeUser(hu);

		em.persist(newImage);
		em.flush();
	}

	// Delete ToDo
	public void destroyTodo(int id, int tId) {
		HomeUser hu = em.find(HomeUser.class, id);
		Todo todo = em.find(Todo.class, tId);

		Collection<Todo> todos = hu.getTodos();

		for (Todo t : todos) {
			if (t.getId() == todo.getId()) {
				todos.remove(t);

				em.remove(todo);
			}
		}
	}

	// Delete Note
	public void destroyNote(int id, int nId) {
		HomeUser hu = em.find(HomeUser.class, id);
		Note note = em.find(Note.class, nId);

		Collection<Note> notes = hu.getNotes();

		for (Note n : notes) {
			if (n.getId() == note.getId()) {
				notes.remove(n);

				em.remove(note);
			}
		}
	}

	// Delete Image
	public void destroyImage(int id, int iId) {
		HomeUser hu = em.find(HomeUser.class, id);
		Image image = em.find(Image.class, iId);

		Collection<Image> images = hu.getImages();

		for (Image i : images) {
			if (i.getId() == image.getId()) {
				images.remove(i);

				em.remove(image);
			}
		}
	}

	// Update ToDo
	public void updateTodo(int id, int tId, Todo todo) {
		Todo editTodo = em.find(Todo.class, tId);

		editTodo.setTask(todo.getTask());
		editTodo.setCompleted(todo.getCompleted());
		editTodo.setDate(todo.getDate());

		em.persist(editTodo);
		em.flush();
	}

	// Update Note
	public void updateNote(int id, int nId, Note note) {
		Note editNote = em.find(Note.class, nId);

		editNote.setNotes(note.getNotes());

		em.persist(editNote);
		em.flush();
	}

}
