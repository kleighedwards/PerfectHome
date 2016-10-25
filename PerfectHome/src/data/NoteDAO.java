package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class NoteDAO {

	@PersistenceContext
	private EntityManager em;
}
