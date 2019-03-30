package ma.pfa.webapp.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.pfa.webapp.dao.IOrigineDao;
import ma.pfa.webapp.model.Origine;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:**/appContextTest.xml" })
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_UNCOMMITTED)
class OrigineDaoImplTest {
	
	@Autowired
	private IOrigineDao origineDao;
	
	static private Origine origineEntity;
	
	
	@Test
	@Rollback(true)
	public void testSaveOrigine() {
		
		Origine org = new Origine();
		org.setNom("Test Origine");
		
		int id = origineDao.save(org);
		
		assertEquals("Test Origine",origineDao.findById(id).getNom());
		
		origineEntity = origineDao.findById(id);
		
	}
	
	@Test
	@Rollback(true)
	public void testUpdateOrigine() {
		
		int id = origineEntity.getId();
		
		origineEntity.setNom("Updated Test Origine");
		
		origineDao.update(origineEntity);
		
		assertEquals("Updated Test Origine",origineDao.findById(id).getNom());
	}
	
	@Test
	@Rollback(true)
	public void testDeleteOrigine() {
		
		int id = origineEntity.getId();
		
		origineDao.delete(origineEntity);
		
		assertNull(origineDao.findById(id));
	}
	

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
