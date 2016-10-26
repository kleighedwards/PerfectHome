package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Home;

public class HomeTest {
	
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
		Home home = em.find(Home.class, 1);
		
		assertEquals(13138711, home.getZpId());
		assertEquals(1, home.getUsers().size());
		assertEquals(2, home.getNotes().size());
		assertEquals(3, home.getTodos().size());
	}
	
	
	@After
	public void tearDown() throws Exception {
		em.close();
        emf.close();
	}
}
