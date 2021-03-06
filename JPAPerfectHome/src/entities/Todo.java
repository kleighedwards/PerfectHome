package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Todo  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private boolean completed;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String task;

	//bi-directional many-to-one association to HomeUser
	@ManyToOne
	@JoinColumn(name="home_user_id")
	@JsonBackReference(value="huTodos")
	private HomeUser homeUser;


	public int getId() {
		return this.id;
	}

	public boolean getCompleted() {
		return this.completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTask() {
		return this.task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public HomeUser getHomeUser() {
		return this.homeUser;
	}

	public void setHomeUser(HomeUser homeUser) {
		this.homeUser = homeUser;
	}

}