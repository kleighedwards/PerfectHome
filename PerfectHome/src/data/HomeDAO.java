package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.Home;

@Transactional
public class HomeDAO {

	@PersistenceContext
	private EntityManager em;

	// Get All Homes
	public List<Home> index() {
		String query = "Select h from Home h";
		List<Home> homes = em.createQuery(query, Home.class).getResultList();

		return homes;
	}

	// Get Home By ID
	public Home show(int id) {
		return em.find(Home.class, id);
	}

	// Add New Home
	public Home create(Home home) {

		em.persist(home);
		em.flush();

		return home;
	}

	// Delete Home
	public void destroy(int id) {
		Home deleteHome = em.find(Home.class, id);

		em.remove(deleteHome);
	}
}
