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
		
		User user =  em.find(User.class, id);
		if (user.getPassword().length() < 25){
			String rawPassword = user.getPassword();
			String encodedPassword = passwordEncoder.encode(rawPassword);
			user.setPassword(encodedPassword);
			em.persist(user);
		}
		
		return user;
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
	public User hasAccount(User user) {
		System.out.println("UserDAO: hasAccount");
		User managedUser = null;
		
		List<User> users = index();
		
		for (User u : users) {
			if (u.getUsername().equals(user.getUsername())) {
				managedUser = em.find(User.class, u.getId());
				System.out.println(user);
				System.out.println(managedUser);
			}
		}
		
		if (managedUser != null){
			String rawPassword = user.getPassword();
			System.out.println("rawPW: " + rawPassword);
			String encodedPassword = managedUser.getPassword();
			System.out.println("encPW: " + encodedPassword);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(encoder.matches(rawPassword,encodedPassword)){
				System.out.println("UserDAO - returning managedUser ie. user with correct password: " + managedUser);
				return managedUser;
			} else {
				User userExists = new User();
				userExists.setPassword("User Exists");
				userExists.setUsername(user.getUsername());
				System.out.println("UserDAO - returning userExists ie. user with incorrect password: " + userExists);
				return userExists;
			}
		}
		
		System.out.println("UserDAO - returning null managedUser ie. user does not exist: " + managedUser);
		return managedUser;
	}

}
