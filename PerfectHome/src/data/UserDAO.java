package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import entities.HomeUser;
import entities.User;

@Transactional
public class UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	// Get All Users
	public List<User> index() {
		String query = "Select u from User u";
		List<User> users = em.createQuery(query, User.class).getResultList();

		return users;
	}

	// Get All Users
	public List<HomeUser> indexHomeUser() {
		String query = "Select hu from HomeUser hu";
		List<HomeUser> homeUsers = em.createQuery(query, HomeUser.class).getResultList();

		return homeUsers;
	}
}
