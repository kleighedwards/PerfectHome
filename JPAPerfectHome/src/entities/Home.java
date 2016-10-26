package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Home  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="cloud_id")
	private String cloudId;

	@Column(name="zp_id")
	private int zpId;

	//bi-directional many-to-many association to User
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="home_user"
		, joinColumns={
			@JoinColumn(name="home_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="user_id")
			}
		)
	private Set<User> users;

	//bi-directional many-to-one association to Note
	@OneToMany(mappedBy="home", fetch=FetchType.EAGER)
	private Set<Note> notes;

	//bi-directional many-to-one association to Todo
	@OneToMany(mappedBy="home", fetch=FetchType.EAGER)
	private Set<Todo> todos;

	public Home() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCloudId() {
		return this.cloudId;
	}

	public void setCloudId(String cloudId) {
		this.cloudId = cloudId;
	}

	public int getZpId() {
		return this.zpId;
	}

	public void setZpId(int zpId) {
		this.zpId = zpId;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public Note addNote(Note note) {
		getNotes().add(note);
		note.setHome(this);

		return note;
	}

	public Note removeNote(Note note) {
		getNotes().remove(note);
		note.setHome(null);

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
		todo.setHome(this);

		return todo;
	}

	public Todo removeTodo(Todo todo) {
		getTodos().remove(todo);
		todo.setHome(null);

		return todo;
	}

}