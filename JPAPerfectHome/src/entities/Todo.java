package entities;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Todo  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte completed;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String task;

	//bi-directional many-to-one association to Home
	@ManyToOne
	private Home home;

	public Todo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getCompleted() {
		return this.completed;
	}

	public void setCompleted(byte completed) {
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

	public Home getHome() {
		return this.home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

}