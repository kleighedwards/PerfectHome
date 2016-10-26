package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="home_user")
public class HomeUser  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Home
	@ManyToOne
	private Home home;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Note
	@OneToMany(mappedBy="homeUser", fetch=FetchType.EAGER)
	private Set<Note> notes;

	//bi-directional many-to-one association to Todo
	@OneToMany(mappedBy="homeUser", fetch=FetchType.EAGER)
	private Set<Todo> todos;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Home getHome() {
		return this.home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public Note addNote(Note note) {
		getNotes().add(note);
		note.setHomeUser(this);

		return note;
	}

	public Note removeNote(Note note) {
		getNotes().remove(note);
		note.setHomeUser(null);

		return note;
	}

	public Set<Todo> getTodos() {
		return this.todos;
	}

	public void setTodos(Set<Todo> todos) {
		this.todos = todos;
	}

	public Todo addTodo(Todo todo) {
		getTodos().add(todo);
		todo.setHomeUser(this);

		return todo;
	}

	public Todo removeTodo(Todo todo) {
		getTodos().remove(todo);
		todo.setHomeUser(null);

		return todo;
	}

}