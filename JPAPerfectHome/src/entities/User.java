package entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private String username;

	//bi-directional many-to-one association to HomeUser
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<HomeUser> homeUsers;


	public int getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<HomeUser> getHomeUsers() {
		return this.homeUsers;
	}

	public void setHomeUsers(Set<HomeUser> homeUsers) {
		this.homeUsers = homeUsers;
	}

	public HomeUser addHomeUser(HomeUser homeUser) {
		getHomeUsers().add(homeUser);
		homeUser.setUser(this);

		return homeUser;
	}

	public HomeUser removeHomeUser(HomeUser homeUser) {
		getHomeUsers().remove(homeUser);
		homeUser.setUser(null);

		return homeUser;
	}

}