package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String url;

	
	// bi-directional many-to-one association to HomeUser
	@ManyToOne
	@JoinColumn(name = "home_user_id")
	@JsonBackReference(value = "huImages")
	private HomeUser homeUser;


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HomeUser getHomeUser() {
		return homeUser;
	}

	public void setHomeUser(HomeUser homeUser) {
		this.homeUser = homeUser;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", url=" + url + "]";
	}
	
}
