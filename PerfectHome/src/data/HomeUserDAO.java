package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import entities.HomeUser;

@Transactional
public class HomeUserDAO {

	@PersistenceContext
	private EntityManager em;

	// Get All HomeUsers
	public List<HomeUser> index() {
		String query = "Select hu from HomeUser hu";
		List<HomeUser> homeUsers = em.createQuery(query, HomeUser.class).getResultList();

		return homeUsers;
	}

	// Get HomeUser By ID
	public HomeUser show(int id) {
		return em.find(HomeUser.class, id);
	}

}
