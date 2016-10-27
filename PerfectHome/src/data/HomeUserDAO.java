package data;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Home;
import entities.HomeUser;
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

	// Delete ToDo
	public void destroyTodo(int id, int tId) {
		HomeUser hu = em.find(HomeUser.class, id);
		Todo todo = em.find(Todo.class, tId);

		Collection<Todo> todos = hu.getTodos();

		for (Todo t : todos) {
			if (t == todo) {
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
			if (n == note) {
				notes.remove(n);

				em.remove(note);
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

	// Update ToDo
	public void updateNote(int id, int nId, Note note) {
		Note editNote = em.find(Note.class, nId);

		editNote.setNotes(note.getNotes());

		em.persist(editNote);
		em.flush();
	}

}
