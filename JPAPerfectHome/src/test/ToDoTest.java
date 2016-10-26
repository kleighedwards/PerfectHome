package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Todo;

public class ToDoTest {

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
		Todo todo = em.find(Todo.class, 1);
		
		assertEquals("Call roofer", todo.getTask());
		assertEquals(0, todo.getCompleted());
		assertEquals(1, todo.getHome().getId());		
	}
	
	@After
	public void tearDown() throws Exception {
		em.close();
        emf.close();
	}


}
