package entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Home  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="cloud_id")
	private String cloudId;

	@Column(name="zp_id")
	private int zpId;

	//bi-directional many-to-one association to HomeUser
	@OneToMany(mappedBy="home", fetch=FetchType.EAGER)
	@JsonManagedReference(value="huHome")
	private Set<HomeUser> homeUsers;

	public int getId() {
		return this.id;
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

	public Set<HomeUser> getHomeUsers() {
		return this.homeUsers;
	}

	public void setHomeUsers(Set<HomeUser> homeUsers) {
		this.homeUsers = homeUsers;
	}

	public HomeUser addHomeUser(HomeUser homeUser) {
		getHomeUsers().add(homeUser);
		homeUser.setHome(this);

		return homeUser;
	}

	public HomeUser removeHomeUser(HomeUser homeUser) {
		getHomeUsers().remove(homeUser);
		homeUser.setHome(null);

		return homeUser;
	}

}