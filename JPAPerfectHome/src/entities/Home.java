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

	@Column(name="zp_id")
	private int zpId;
	
	private String address;
	
	@Column(name="zillow_image")
	private String zillowImage;

	//bi-directional many-to-one association to HomeUser
	@OneToMany(mappedBy="home", fetch=FetchType.EAGER, orphanRemoval=true)
	@JsonManagedReference(value="huHome")
	private Set<HomeUser> homeUsers;

	public int getId() {
		return this.id;
	}

	public int getZpId() {
		return this.zpId;
	}

	public void setZpId(int zpId) {
		this.zpId = zpId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZillowImage() {
		return zillowImage;
	}

	public void setZillowImage(String zillowImage) {
		this.zillowImage = zillowImage;
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