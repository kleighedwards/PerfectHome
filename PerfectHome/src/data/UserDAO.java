package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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

	// Get User By ID
	public User show(int id) {
		return em.find(User.class, id);
	}

	// Add New User
	public User create(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);

		em.persist(user);
		em.flush();

		return user;
	}

	// Delete User
	public void destroy(int id) {
		User deleteUser = em.find(User.class, id);
				
		em.remove(deleteUser);
	}

	// Authenticate User
	public User authenticateUser(User user) {
		User managedUser = null;

		List<User> users = index();

		for (User u : users) {
			if (u.getUsername().equals(user.getUsername())) {
				managedUser = em.find(User.class, u.getId());
			}
		}

		if (managedUser != null) {
			String rawPassword = user.getPassword();
			String encodedPassword = managedUser.getPassword();

			if (passwordEncoder.matches(rawPassword, encodedPassword)) {
				return managedUser;
			}
		}

		return managedUser;
	}

}
