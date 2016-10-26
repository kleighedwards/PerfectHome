package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Note  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String notes;

	//bi-directional many-to-one association to HomeUser
	@ManyToOne
	@JoinColumn(name="home_user_id")
	private HomeUser homeUser;


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public HomeUser getHomeUser() {
		return this.homeUser;
	}

	public void setHomeUser(HomeUser homeUser) {
		this.homeUser = homeUser;
	}

}