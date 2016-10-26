package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.HomeUser;

public class HomeUserTest {
	
	private EntityManagerFactory emf;
    private EntityManager em;

	@Before
	public void setUp() throws Exception {
		emf = Persistence.
	            createEntityManagerFactory("JPAPerfectHome");
	    em = emf.createEntityManager();
	}

	@Test
	public void test() {
		HomeUser homeUser = em.find(HomeUser.class, 1);
		
		assertEquals("John", homeUser.getUser().getFirstName());
		assertEquals(13138711, homeUser.getHome().getZpId());
		assertEquals(2, homeUser.getNotes().size());
		assertEquals(3, homeUser.getTodos().size());
	}
	
	@After
	public void tearDown() throws Exception {
		em.close();
        emf.close();
	}
}
