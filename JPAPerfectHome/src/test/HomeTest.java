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
		
		assertEquals(7796919, home.getZpId());
		assertEquals("6303 N 15th St", home.getAddress());
		assertEquals("http://photos.zillowstatic.com/p_d/IS2ny0atnkvzz90000000000.jpg", home.getZillowImage());
		
		
//		assertEquals(1, home.getHomeUsers().size());
	}
	
	
	@After
	public void tearDown() throws Exception {
		em.close();
        emf.close();
	}
}
