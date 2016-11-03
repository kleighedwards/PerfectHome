package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	// Get HomeUser By User ID and Home ID
	public HomeUser showByIds(int uId, int hId) {
		String query = "SELECT hu FROM HomeUser hu WHERE hu.user.id = ?1 AND hu.home.id = ?2";

		HomeUser hu = em.createQuery(query, HomeUser.class).setParameter(1, uId).setParameter(2, hId).getSingleResult();

		return hu;
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

	// Delete HomeUser By User ID and Home ID
	public void destroyByIds(int uId, int hId) {
		User user = em.find(User.class, uId);
		Home home = em.find(Home.class, hId);

		HomeUser hu = showByIds(user.getId(), home.getId());

		Set<HomeUser> hus = user.getHomeUsers();

		hus.remove(hu);
		user.setHomeUsers(hus);

		em.persist(user);
	}

	// Create A New ToDo
	public void createTodo(int id, Todo newTodo) {
		HomeUser hu = em.find(HomeUser.class, id);

		newTodo.setHomeUser(hu);

		em.persist(newTodo);
		em.flush();
	}

	// Create A New ToDo By User ID and Home ID
	public void createTodoByIds(int uId, int hId, Todo newTodo) {
		User user = em.find(User.class, uId);
		Home home = em.find(Home.class, hId);

		HomeUser hu = showByIds(user.getId(), home.getId());

		newTodo.setHomeUser(hu);

		em.persist(newTodo);
		em.flush();
	}

	// Create A New Note
	public void createNote(int id, Note newNote) {
		HomeUser hu = em.find(HomeUser.class, id);

		newNote.setHomeUser(hu);

		em.persist(newNote);
		em.flush();
	}

	// Create A New Note By User ID and Home ID
	public void createNoteByIds(int uId, int hId, Note newNote) {
		User user = em.find(User.class, uId);
		Home home = em.find(Home.class, hId);

		HomeUser hu = showByIds(user.getId(), home.getId());

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

	// Create A New Note By User ID and Home ID
	public void createImageByIds(int uId, int hId, Image newImage) {
		User user = em.find(User.class, uId);
		Home home = em.find(Home.class, hId);

		HomeUser hu = showByIds(user.getId(), home.getId());

		newImage.setHomeUser(hu);

		em.persist(newImage);
		em.flush();
	}

	// Delete ToDo
	public void destroyTodo(int id, int tId) {
		System.out.println("HomeUSerDAO - Delete ToDo: ");
		HomeUser hu = em.find(HomeUser.class, id);
		Todo todo = em.find(Todo.class, tId);

		Set<Todo> todos = hu.getTodos();
		
		Todo deleteTodo = new Todo();

		for (Todo t : todos) {
			if (t.getId() == todo.getId()) {
				deleteTodo = t;
				break;
			}
		}
		
		if (deleteTodo != null) {	
			todos.remove(deleteTodo);
		}
		
		hu.setTodos(todos);
		em.persist(hu);
	}

	// Delete ToDo By User ID and Home ID
	public void destroyTodoByIds(int uId, int hId, int tId) {
		User user = em.find(User.class, uId);
		Home home = em.find(Home.class, hId);

		HomeUser hu = showByIds(user.getId(), home.getId());
		Todo todo = em.find(Todo.class, tId);
		Todo deleteTodo = new Todo();


		Set<Todo> todos = hu.getTodos();

		for (Todo t : todos) {
			if (t.getId() == todo.getId()) {
				deleteTodo = t;
				break;
			}
		}
		
		if (deleteTodo != null) {	
			todos.remove(deleteTodo);
		}
		
		hu.setTodos(todos);
		em.persist(hu);

	}

	// Delete Note
	public void destroyNote(int id, int nId) {
		HomeUser hu = em.find(HomeUser.class, id);
		Note note = em.find(Note.class, nId);

		Set<Note> notes = hu.getNotes();
		Note deleteNote = new Note();

		for (Note n : notes) {
			if (n.getId() == note.getId()) {
				deleteNote = n;
				break;
			}
		}
		
		if (deleteNote != null) {
			notes.remove(deleteNote);
		}
		
		hu.setNotes(notes);
		em.persist(hu);
	}

	// Delete Note By User ID and Home ID
	public void destroyNoteByIds(int uId, int hId, int nId) {
		User user = em.find(User.class, uId);
		Home home = em.find(Home.class, hId);

		HomeUser hu = showByIds(user.getId(), home.getId());
		Note note = em.find(Note.class, nId);
		Note deleteNote = new Note();

		Set<Note> notes = hu.getNotes();

		for (Note n : notes) {
			if (n.getId() == note.getId()) {
				deleteNote = n;
				
				break;
			}
		}
		
		if (deleteNote != null) {	
			notes.remove(deleteNote);
		}
		
		hu.setNotes(notes);
		em.persist(hu);
	}

	// Delete Image
	public void destroyImage(int id, int iId) {
		HomeUser hu = em.find(HomeUser.class, id);
		Image image = em.find(Image.class, iId);

		Set<Image> images = hu.getImages();
		Image deleteImage = new Image();

		for (Image i : images) {
			if (i.getId() == image.getId()) {
				deleteImage = i;

				break;
			}
		}
		
		if (deleteImage != null) {
			images.remove(deleteImage);
		}
		
		hu.setImages(images);
		em.persist(hu);
	}

	// Delete Image By User ID and Home ID
	public void destroyImageByIds(int uId, int hId, int iId) {
		User user = em.find(User.class, uId);
		Home home = em.find(Home.class, hId);

		HomeUser hu = showByIds(user.getId(), home.getId());
		Image image = em.find(Image.class, iId);
		Image deleteImage = new Image();

		Set<Image> images = hu.getImages();

		for (Image i : images) {
			if (i.getId() == image.getId()) {
				deleteImage = i;

				break;
			}
		}
		
		if (deleteImage != null) {
			images.remove(deleteImage);
		}
		
		hu.setImages(images);
		em.persist(hu);
	}

	// Update ToDo
	public void updateTodo(int tId, Todo todo) {
		Todo editTodo = em.find(Todo.class, tId);

		editTodo.setTask(todo.getTask());
		editTodo.setCompleted(todo.getCompleted());
		
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String d = s.format(todo.getDate());
        String day = Integer.parseInt(d.charAt(d.length()-2)+""+ d.charAt(d.length()-1))+1 + "";
        d = d.substring(0, d.length()-2) + day;
        Date date;
		try {
			date = s.parse(d);
			editTodo.setDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
//		editTodo.setDate(todo.getDate());

		em.persist(editTodo);
		em.flush();
	}

	// Update Note
	public void updateNote(int nId, Note note) {
		Note editNote = em.find(Note.class, nId);

		editNote.setNotes(note.getNotes());

		em.persist(editNote);
		em.flush();
	}

	// Update Rating
	public void updateRating(int id, int rating) {
		HomeUser hu = em.find(HomeUser.class, id);

		hu.setRating(rating);

		em.persist(hu);
		em.flush();
	}

	// Update Rating By IDs
	public void updateRatingByIds(int uId, int hId, int rating) {
		User user = em.find(User.class, uId);
		Home home = em.find(Home.class, hId);

		HomeUser hu = showByIds(user.getId(), home.getId());
		
		hu.setRating(rating);

		em.persist(hu);
		em.flush();
	}

}
