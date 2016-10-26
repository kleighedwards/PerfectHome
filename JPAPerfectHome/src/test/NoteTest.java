package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Note;

public class NoteTest {
	
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
		Note note = em.find(Note.class, 1);
		
		assertEquals("Loved the master bathroom", note.getNotes());
		assertEquals(1, note.getHome().getId());
	}
	
	@After
	public void tearDown() throws Exception {
		em.close();
        emf.close();
	}
}
